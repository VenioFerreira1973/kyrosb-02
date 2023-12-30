package com.kyros.api.api.assemblers.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.kyros.api.api.controller.ProdutoController;
import com.kyros.api.api.model.model.ProdutoModel;
import com.kyros.api.domain.model.Produto;

@Component
public class ProdutoModelAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	public ProdutoModelAssembler() {
		super(ProdutoController.class, ProdutoModel.class);
	}

	@Override
	public ProdutoModel toModel(Produto produto) {
		ProdutoModel produtoModel = createModelWithId(produto.getId(), produto);

		modelMapper.map(produto, produtoModel);

		return produtoModel;
	}
	

}
