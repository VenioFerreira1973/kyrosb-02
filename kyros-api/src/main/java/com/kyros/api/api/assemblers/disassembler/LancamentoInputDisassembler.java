package com.kyros.api.api.assemblers.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kyros.api.api.model.input.LancamentoInput;
import com.kyros.api.domain.model.Lancamento;

@Component
public class LancamentoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Lancamento toDomainObject(LancamentoInput lancamentoInput) {
		
		return modelMapper.map(lancamentoInput, Lancamento.class);
	}
	
	public void copyToDomainObject(LancamentoInput lancamentoInput, Lancamento lancamento) {
		
		modelMapper.map(lancamentoInput, lancamento);
	}

}
