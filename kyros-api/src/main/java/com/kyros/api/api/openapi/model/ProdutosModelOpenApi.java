package com.kyros.api.api.openapi.model;

import java.util.List;

import com.kyros.api.api.model.model.ProdutoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("ProdutosModel")
@Data
public class ProdutosModelOpenApi {

	//private ProdutosEmbeddedModel _embedded;
	
	//@ApiModel("ProdutosEmbeddedModel")
	@Data
	public class ProdutosEmbeddedModel {
		private List<ProdutoModel> produtos;
	}
}
