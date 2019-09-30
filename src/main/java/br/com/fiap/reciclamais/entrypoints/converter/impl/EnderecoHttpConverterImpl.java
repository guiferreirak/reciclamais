package br.com.fiap.reciclamais.entrypoints.converter.impl;

import br.com.fiap.reciclamais.entrypoints.converter.EnderecoHttpConverter;
import br.com.fiap.reciclamais.usecase.data.input.EnderecoBusinessInput;
import org.springframework.stereotype.Component;

@Component
public class EnderecoHttpConverterImpl implements EnderecoHttpConverter {

    @Override
    public EnderecoBusinessInput parseEndereco(String cep, String rua, Integer numero, String estado, String cidade) {
        return EnderecoBusinessInput
                .builder()
                .cep(cep)
                .rua(rua)
                .numero(numero)
                .estado(estado)
                .cidade(cidade)
                .build();
    }

}
