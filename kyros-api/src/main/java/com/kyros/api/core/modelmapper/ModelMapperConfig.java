package com.kyros.api.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kyros.api.api.model.input.LancamentoInput;
import com.kyros.api.domain.model.Lancamento;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        modelMapper.createTypeMap(Lancamento.class, LancamentoInput.class)
            .addMapping(Lancamento::getCliente, LancamentoInput::setClienteId)
        	.addMapping(Lancamento::getProduto, LancamentoInput::setProdutoId);
        
        return modelMapper;
    }
}