package com.kyros.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyros.api.domain.exception.LancamentoNaoEncontradaException;
import com.kyros.api.domain.model.Cliente;
import com.kyros.api.domain.model.Lancamento;
import com.kyros.api.domain.model.Produto;
import com.kyros.api.domain.repository.LancamentoRepository;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private CadastroClienteService cadastroCliente;

	@Autowired
	private CadastroProdutoService cadastroProduto;

	@Transactional
	public Lancamento salvar(Lancamento lancamento) {
		return lancamentoRepository.save(lancamento);
		
	}
	
	@Transactional
	public Lancamento emitir(Lancamento lancamento) {
		validarLancamento(lancamento);
		
		return lancamentoRepository.save(lancamento);
	}

	private void validarLancamento(Lancamento lancamento) {
				
		Cliente cliente = cadastroCliente.buscarOuFalhar(lancamento.getCliente().getId());
		Produto produto = cadastroProduto.buscarOuFalhar(lancamento.getProduto().getId());
		
		lancamento.setCliente(cliente);
		lancamento.setProduto(produto);		
	}
	
	public Lancamento buscarOuFalhar(Long id) {
		return lancamentoRepository.findById(id)
			.orElseThrow(() -> new LancamentoNaoEncontradaException(id));
	}
}
