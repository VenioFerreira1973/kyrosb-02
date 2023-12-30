package com.kyros.api.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.kyros.api.domain.model.Lancamento;

public interface LancamentoRepository extends CustomJpaRepository<Lancamento, Long>,
	JpaSpecificationExecutor<Lancamento>{
	
	Optional<Lancamento> findById(Long id);
	
	@Query("from Lancamento c join fetch c.cliente join fetch c.produto p")
	List<Lancamento> findAll();
	

}
