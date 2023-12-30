package com.kyros.api.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Lancamento extends AbstractAggregateRoot<Lancamento> {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Produto produto;

	@CreationTimestamp
	private OffsetDateTime dataVenda;
	
	@Column(nullable = false)
	private Integer quantidadeVendida;
	
	@Column
	private BigDecimal valorTotalVenda;	
		
}