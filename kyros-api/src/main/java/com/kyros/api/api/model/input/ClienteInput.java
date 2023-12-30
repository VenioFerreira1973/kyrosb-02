package com.kyros.api.api.model.input;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteInput {
	
	@ApiModelProperty(example = "Vênio Ferreira")
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "2002-10-30")
	@NotNull
	private OffsetDateTime dataNascimento;
	
	@ApiModelProperty(example = "Rua Afonso Pena, 200 - Centro - Uberlândia")
	@NotBlank
	private String endereco;
	
	@ApiModelProperty(example = "954.966.359-00")
	@NotBlank
	private String cpfCnpj;
	
	@ApiModelProperty(example = "fisica")
	@NotBlank
	private String tipoPessoa;
	
}
