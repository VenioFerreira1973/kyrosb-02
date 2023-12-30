package com.kyros.api.domain.model;


import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, columnDefinition = "datetime")
	//@JsonFormat(pattern = "yyyy-MM-dd")
	//@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	//@JsonDeserialize(using = OffsetDateTimeDeserializer.class)
	private OffsetDateTime dataNascimento;
	
	@Column(nullable = false)
	private String endereco;
	
	@Column(nullable = false)
	private String cpfCnpj;
	
	@Column(nullable = false)
	private String tipoPessoa;
		
}
