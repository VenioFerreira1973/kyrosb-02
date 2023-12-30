package com.kyros.api.domain.exception;

public class LancamentoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public LancamentoNaoEncontradaException(Long id) {
		super(String.format("Não existe um cadastro de lançamento com código %d", id));
	}

}
