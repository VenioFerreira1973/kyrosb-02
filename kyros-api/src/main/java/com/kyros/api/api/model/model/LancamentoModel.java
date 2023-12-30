package com.kyros.api.api.model.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "lancamentos")
@Setter
@Getter
public class LancamentoModel extends RepresentationModel<LancamentoModel>{
	
	private Long id;
	
	private ClienteModel cliente;
	
	private ProdutoModel produto;
	
	private OffsetDateTime dataVenda;
	
	private Integer quantidadeVendida;
	
	private BigDecimal valorTotalVenda;
	
}
