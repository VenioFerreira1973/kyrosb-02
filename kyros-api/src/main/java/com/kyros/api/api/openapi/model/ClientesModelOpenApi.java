package com.kyros.api.api.openapi.model;

import java.util.List;

import com.kyros.api.api.model.model.ClienteModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("ClientesModel")
@Data
public class ClientesModelOpenApi {

	private ClientesEmbeddedModelOpenApi _embedded;

	@ApiModel("ClientesEmbeddedModel")
	@Data
	public class ClientesEmbeddedModelOpenApi {

		private List<ClienteModel> clientes;

	}
}
