package com.kyros.api.api.model.input;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LancamentoInput {

	@Valid
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long clienteId;	
	
	@Valid
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long produtoId;
	
	@ApiModelProperty(example = "2023-12-26", required = true)
	@NotNull
	private OffsetDateTime dataVenda;
	
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Integer quantidadeVendida;
	
	@ApiModelProperty(example = "100.00", required = true)
	@NotNull
	private BigDecimal valorTotalVenda;
	
	
	
	
}
