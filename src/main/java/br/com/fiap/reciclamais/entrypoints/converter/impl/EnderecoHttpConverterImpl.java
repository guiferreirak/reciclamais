package br.com.fiap.reciclamais.entrypoints.converter.impl;

import br.com.fiap.reciclamais.entrypoints.converter.EnderecoHttpConverter;
import br.com.fiap.reciclamais.entrypoints.data.response.EnderecoContractResponse;
import br.com.fiap.reciclamais.usecase.data.input.EnderecoBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.EnderecoBusinessOutput;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

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

    @Override
    public EnderecoContractResponse parseEndereco(EnderecoBusinessOutput enderecoBusiness) {
        if (isNull(enderecoBusiness))
            return null;

        return EnderecoContractResponse
                .builder()
                .cep(enderecoBusiness.getCep())
                .rua(enderecoBusiness.getRua())
                .numero(enderecoBusiness.getNumero())
                .cidade(enderecoBusiness.getCidade())
                .estado(enderecoBusiness.getEstado())
                .build();
    }

}
