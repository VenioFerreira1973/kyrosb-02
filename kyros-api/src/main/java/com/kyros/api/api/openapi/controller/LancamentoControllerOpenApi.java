package com.kyros.api.api.openapi.controller;

import java.util.List;
import java.util.Map;

import com.kyros.api.api.exceptionHandler.Problem;
import com.kyros.api.api.model.input.LancamentoInput;
import com.kyros.api.api.model.model.LancamentoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Lancamentos")
public interface LancamentoControllerOpenApi {
	
	@ApiOperation("Listar lancamentos")
	public Map<String, List<LancamentoModel>> listar();
	
	@ApiOperation("Buscar uma lancamento pelo ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da lancamento inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Lancamento não encontrada", response = Problem.class)
	})
	LancamentoModel buscar(
	            @ApiParam(value = "Id de uma lancamento", example = "1", required = true)
	            Long id);   
	
	@ApiOperation("Excluir uma lancamento")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Lancamento excluída com sucesso", response = Problem.class),
		@ApiResponse(code = 400, message = "ID de lancamento inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "ID de lancamento não encontrado", response = Problem.class)
	})
	void excluir(@ApiParam(value = "ID de uma lancamento", example = "1", required = true) Long Id);
	
	@ApiOperation("Efetuar uma lancamento")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Lancamento efetuada com sucesso", response = Problem.class),
	})
	public LancamentoModel efetuarLancamento(
				@ApiParam(value = "Representação de uma nova lancamento", name = "corpo", required = true) 
				LancamentoInput lancamentoInput);
}
