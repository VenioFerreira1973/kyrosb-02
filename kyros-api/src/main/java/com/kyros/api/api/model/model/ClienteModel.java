package com.kyros.api.api.model.model;

import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "clientes")
@Setter
@Getter
public class ClienteModel extends RepresentationModel<ClienteModel>{
	
	private Long id;
	
	private String nome;
	
	private OffsetDateTime dataNascimento;
	
	private String endereco;
	
	private String cpfCnpj;
	
	private String tipoPessoa;
	
	
}
