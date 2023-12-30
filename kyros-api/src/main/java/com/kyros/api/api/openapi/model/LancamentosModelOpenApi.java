package com.kyros.api.api.openapi.model;

import java.util.List;

import com.kyros.api.api.model.model.LancamentoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("LancamentosResumoModel")
@Data
public class LancamentosModelOpenApi {
	
    //private LancamentosResumoEmbeddedModelOpenApi _embedded;
    private PageModelOpenApi page;
    
    //@ApiModel("LancamentosResumoEmbeddedModel")
    @Data
    public class LancamentosEmbeddedModelOpenApi {
        
        private List<LancamentoModel> lancamentos;
        
    }  
	    
}  


