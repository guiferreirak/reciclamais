package br.com.fiap.reciclamais.usecase.converter.impl;

import br.com.fiap.reciclamais.gateway.repository.data.EnderecoDocument;
import br.com.fiap.reciclamais.usecase.converter.EnderecoUseCaseConverter;
import br.com.fiap.reciclamais.usecase.data.input.EnderecoBusinessInput;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class EnderecoUseCaseConverterImpl implements EnderecoUseCaseConverter {

    @Override
    public EnderecoDocument parseEndereco(EnderecoBusinessInput endereco) {
        if(isNull(endereco))
            return null;

        return EnderecoDocument
                .builder()
                .cep(endereco.getCep())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .estado(endereco.getEstado())
                .cidade(endereco.getCidade())
                .build();
    }

    @Override
    public EnderecoDocument parseEndereco(EnderecoDocument endereco) {
        return EnderecoDocument
                .builder()
                .cep(endereco.getCep())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .estado(endereco.getEstado())
                .cidade(endereco.getCidade())
                .build();
    }

}
