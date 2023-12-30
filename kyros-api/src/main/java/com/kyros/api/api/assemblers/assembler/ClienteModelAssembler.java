package com.kyros.api.api.assemblers.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kyros.api.api.controller.ClienteController;
import com.kyros.api.api.model.model.ClienteModel;
import com.kyros.api.domain.model.Cliente;

@Component
public class ClienteModelAssembler extends RepresentationModelAssemblerSupport<Cliente, ClienteModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public ClienteModelAssembler() {
		super(ClienteController.class, ClienteModel.class);
	}

	@Override
	public ClienteModel toModel(Cliente cliente) {
		
		ClienteModel clienteModel = createModelWithId(cliente.getId(), cliente);
		
		modelMapper.map(cliente, clienteModel);
		
		return clienteModel; 
	}
	
}
