package com.kyros.api.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoInput {
			
	@ApiModelProperty(example = "Celular xpto", required = true)
	@NotBlank
	private String nome;

	@ApiModelProperty(example = "Celular legal pra caramba", required = true)
	@NotBlank
	private String descricao;
	
	@ApiModelProperty(example = "Ativo")
	@NotNull
	private String status;
	
	@ApiModelProperty(example = "12.50")
	@PositiveOrZero
	private BigDecimal valorUnidade;
		
	
}
