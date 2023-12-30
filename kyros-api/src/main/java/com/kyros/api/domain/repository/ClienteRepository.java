package com.kyros.api.domain.repository;

import java.util.Optional;

import com.kyros.api.domain.model.Cliente;

public interface ClienteRepository extends CustomJpaRepository<Cliente, Long>{
	
	Optional<Cliente> findByCpfCnpj(String cpfCnpj);
	
}
