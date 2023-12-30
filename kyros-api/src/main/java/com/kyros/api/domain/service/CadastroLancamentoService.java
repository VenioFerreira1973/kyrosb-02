package com.kyros.api.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyros.api.domain.exception.LancamentoNaoEncontradaException;
import com.kyros.api.domain.exception.NegocioException;
import com.kyros.api.domain.model.Lancamento;
import com.kyros.api.domain.repository.LancamentoRepository;

@Service
public class CadastroLancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	
	public List<Lancamento> todos(){
		return lancamentoRepository.findAll();
	}
	
	@Transactional
	public void excluir(Long Id) {
		
		try {
			lancamentoRepository.deleteById(Id);
			lancamentoRepository.flush();
			
		}catch(EmptyResultDataAccessException e) {
			throw new LancamentoNaoEncontradaException(Id);
			
		}
	}
	
	@Transactional
	public Lancamento salvar(Lancamento lancamento) {
		
		lancamentoRepository.detach(lancamento);
		
		Optional<Lancamento> lancamentoExistente = lancamentoRepository.findById(lancamento.getId());
		
		if(lancamentoExistente.isPresent() && !lancamentoExistente.get().equals(lancamento)) {
			throw new NegocioException(
					String.format("Já existe cliente cadastrado com o CPF/CNPJ %s", lancamento.getId()));
 			
		}

		return lancamentoRepository.save(lancamento);
		
	}
	
	
	public Lancamento buscarOuFalhar(Long id) {
		return lancamentoRepository.findById(id)
			.orElseThrow(() -> new LancamentoNaoEncontradaException(id));
	}
	
}
