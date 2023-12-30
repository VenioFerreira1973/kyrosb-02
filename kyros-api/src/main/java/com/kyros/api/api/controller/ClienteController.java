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

import com.kyros.api.api.assemblers.assembler.ClienteModelAssembler;
import com.kyros.api.api.assemblers.disassembler.ClienteInputDisassembler;
import com.kyros.api.api.model.input.ClienteInput;
import com.kyros.api.api.model.model.ClienteModel;
import com.kyros.api.api.openapi.controller.ClienteControllerOpenApi;
import com.kyros.api.domain.exception.ClienteNaoEncontradoException;
import com.kyros.api.domain.exception.NegocioException;
import com.kyros.api.domain.model.Cliente;
import com.kyros.api.domain.service.CadastroClienteService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController implements ClienteControllerOpenApi {

	@Autowired
	private CadastroClienteService cadastroCliente;

	@Autowired
	private ClienteModelAssembler clienteModelAssembler;

	@Autowired
	private ClienteInputDisassembler clienteInputDisassembler;
	
	@ResponseBody
    @GetMapping
    @Override
    public Map<String, List<ClienteModel>> listar() {
        List<ClienteModel> todosClientes = cadastroCliente.todos().stream()
            .map(clienteModelAssembler::toModel)
            .collect(Collectors.toList());
        
        Map<String, List<ClienteModel>> result = new HashMap<>();
        result.put("clientes", todosClientes);
        
        return result;
    }
	
	@Override
	@GetMapping("/{Id}")
	public ClienteModel buscar(@PathVariable Long Id) {

		try {
			
			Cliente cliente = cadastroCliente.buscarOuFalhar(Id);
			return clienteModelAssembler.toModel(cliente);
		} catch (ClienteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteModel adicionar(@RequestBody @Valid ClienteInput clienteInput) {

			try {
			
			Cliente cliente = clienteInputDisassembler.toDomainObject(clienteInput);
			cliente = cadastroCliente.salvar(cliente);
			ClienteModel clienteModel = clienteModelAssembler.toModel(cliente);
			return clienteModel;
			

		} catch (ClienteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/update/{clienteId}")
	public ClienteModel atualizar(@PathVariable Long clienteId, @RequestBody @Valid ClienteInput clienteInput) {
		
		try {
			
			Cliente clienteAtual = cadastroCliente.buscarOuFalhar(clienteId);
			clienteInputDisassembler.copyToDomainObject(clienteInput, clienteAtual);
			clienteAtual = cadastroCliente.salvar(clienteAtual);
			return clienteModelAssembler.toModel(clienteAtual);
		} catch (ClienteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@DeleteMapping("/delete/{Id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long Id) {
		cadastroCliente.excluir(Id);

	}

}
