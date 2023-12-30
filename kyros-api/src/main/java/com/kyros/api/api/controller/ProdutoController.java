package com.kyros.api.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kyros.api.api.assemblers.assembler.ProdutoModelAssembler;
import com.kyros.api.api.assemblers.disassembler.ProdutoInputDisassembler;
import com.kyros.api.api.model.input.ProdutoInput;
import com.kyros.api.api.model.model.ProdutoModel;
import com.kyros.api.api.openapi.controller.ProdutoControllerOpenApi;
import com.kyros.api.domain.exception.NegocioException;
import com.kyros.api.domain.exception.ProdutoNaoEncontradoException;
import com.kyros.api.domain.model.Produto;
import com.kyros.api.domain.repository.ProdutoRepository;
import com.kyros.api.domain.service.CadastroProdutoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController implements ProdutoControllerOpenApi {

	@Autowired
	CadastroProdutoService cadastroProduto;
	
	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;

	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;	

    public ProdutoController(ProdutoRepository produtoRepository, ProdutoModelAssembler produtoModelAssembler) {
        this.produtoModelAssembler = produtoModelAssembler;
    }
    
    @ResponseBody
    @GetMapping
    @Override
    public Map<String, List<ProdutoModel>> listar() {
        List<ProdutoModel> listaProdutos = cadastroProduto.todos().stream()
            .map(produtoModelAssembler::toModel)
            .collect(Collectors.toList());
        
        Map<String, List<ProdutoModel>> result = new HashMap<>();
        result.put("produtos", listaProdutos);
        
        return result;
    }
	
	@Override
	@GetMapping("/{produtoId}")
	public ProdutoModel buscar(@PathVariable Long produtoId) {

		try {
			Produto produto = cadastroProduto.buscarOuFalhar(produtoId);
			return produtoModelAssembler.toModel(produto);
		} catch (ProdutoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Override
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel adicionar(@RequestBody @Valid ProdutoInput produtoInput) {

		try {
			Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
			produto = cadastroProduto.salvar(produto);
			return produtoModelAssembler.toModel(produto);

		} catch (ProdutoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}

	}

	@PutMapping("/update/{produtoId}")
	@Override
	public ProdutoModel atualizar(@PathVariable Long produtoId, @RequestBody @Valid ProdutoInput produtoInput) {

		try {

			Produto produtoAtual = cadastroProduto.buscarOuFalhar(produtoId);
			produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);
			produtoAtual = cadastroProduto.salvar(produtoAtual);
			return produtoModelAssembler.toModel(produtoAtual);
		} catch (ProdutoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/delete/{Id}")
	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long Id) {
		cadastroProduto.excluir(Id);

	}

}
