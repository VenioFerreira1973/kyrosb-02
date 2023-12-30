package com.kyros.api.api.assemblers.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kyros.api.api.controller.LancamentoController;
import com.kyros.api.api.model.model.LancamentoModel;
import com.kyros.api.domain.model.Lancamento;

@Component
public class LancamentoModelAssembler extends RepresentationModelAssemblerSupport<Lancamento, LancamentoModel> {

	@Autowired
	private ModelMapper modelMapper;
	

	public LancamentoModelAssembler() {
		super(LancamentoController.class, LancamentoModel.class);
	}

	@Override
	public LancamentoModel toModel(Lancamento lancamento) {

		LancamentoModel lancamentoModel = createModelWithId(lancamento.getId(), lancamento);
		
		modelMapper.map(lancamento, lancamentoModel);
		
		return lancamentoModel;
	}	

}
