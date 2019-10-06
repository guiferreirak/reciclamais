package br.com.fiap.reciclamais.entrypoints.converter.impl;

import br.com.fiap.reciclamais.entrypoints.converter.PontuacaoHttpConverter;
import br.com.fiap.reciclamais.entrypoints.data.request.pontuacao.RegistroPontuacaoContractRequest;
import br.com.fiap.reciclamais.usecase.data.input.pontuacao.RegistroPontuacaoBusinessInput;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class PontuacaoHttpConverterImpl implements PontuacaoHttpConverter {

    @Override
    public List<RegistroPontuacaoBusinessInput> toListRegistroPontuacaoBusinessInput(List<RegistroPontuacaoContractRequest> pontuacoes) {
        if(isNull(pontuacoes) || pontuacoes.isEmpty())
            return null;

        return pontuacoes.stream().map(this::toRegistroPontuacaoBusinessInput).collect(Collectors.toList());
    }

    private RegistroPontuacaoBusinessInput toRegistroPontuacaoBusinessInput(RegistroPontuacaoContractRequest pontuacao) {
        return RegistroPontuacaoBusinessInput
                .builder()
                .uuid(pontuacao.getUuid())
                .data(pontuacao.getData())
                .build();
    }

}
