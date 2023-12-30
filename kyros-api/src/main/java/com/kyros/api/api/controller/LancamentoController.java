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

import com.kyros.api.api.assemblers.assembler.LancamentoModelAssembler;
import com.kyros.api.api.assemblers.disassembler.LancamentoInputDisassembler;
import com.kyros.api.api.model.input.LancamentoInput;
import com.kyros.api.api.model.model.LancamentoModel;
import com.kyros.api.api.openapi.controller.LancamentoControllerOpenApi;
import com.kyros.api.domain.exception.ClienteNaoEncontradoException;
import com.kyros.api.domain.exception.LancamentoNaoEncontradaException;
import com.kyros.api.domain.exception.NegocioException;
import com.kyros.api.domain.model.Lancamento;
import com.kyros.api.domain.service.CadastroLancamentoService;
import com.kyros.api.domain.service.LancamentoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/lancamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class LancamentoController implements LancamentoControllerOpenApi{

	@Autowired
	private CadastroLancamentoService cadastroLancamento;
	
	@Autowired
	private LancamentoService emissaoLancamento;
	
	
	@Autowired
	private LancamentoModelAssembler lancamentoModelAssembler;
	
	@Autowired
	private LancamentoInputDisassembler lancamentoInputDisassembler;
		
	@ResponseBody
    @GetMapping
    @Override
    public Map<String, List<LancamentoModel>> listar() {
        List<LancamentoModel> listaLancamentos = cadastroLancamento.todos().stream()
            .map(lancamentoModelAssembler::toModel)
            .collect(Collectors.toList());
        
        Map<String, List<LancamentoModel>> result = new HashMap<>();
        result.put("lancamentos", listaLancamentos);
        
        return result;
    }
	
	
	@Override
	@GetMapping("/{id}")
	public LancamentoModel buscar(@PathVariable Long id) {
		
		try {
			Lancamento lancamento = cadastroLancamento.buscarOuFalhar(id);
			return lancamentoModelAssembler.toModel(lancamento);
		} catch (LancamentoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/delete/{Id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long Id) {
		cadastroLancamento.excluir(Id);

	}
	
	@PostMapping("/efetuar-lancamento")
	@ResponseStatus(HttpStatus.CREATED)
	public LancamentoModel efetuarLancamento(@Valid @RequestBody LancamentoInput lancamentoInput) {

		Lancamento novoLancamento = lancamentoInputDisassembler.toDomainObject(lancamentoInput);
        novoLancamento = emissaoLancamento.emitir(novoLancamento);
        return lancamentoModelAssembler.toModel(novoLancamento);
	    
	}
	
	@PutMapping("/update/{lancamentoId}")
	public LancamentoModel atualizar(@PathVariable Long lancamentoId, @RequestBody @Valid LancamentoInput lancamentoInput) {
		
		try {
			
			Lancamento lancamentoAtual = cadastroLancamento.buscarOuFalhar(lancamentoId);
			lancamentoInputDisassembler.copyToDomainObject(lancamentoInput, lancamentoAtual);
			lancamentoAtual = cadastroLancamento.salvar(lancamentoAtual);
			return lancamentoModelAssembler.toModel(lancamentoAtual);
		} catch (ClienteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
}
