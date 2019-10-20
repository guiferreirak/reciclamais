package br.com.fiap.reciclamais.entrypoints.converter.impl;

import br.com.fiap.reciclamais.entrypoints.converter.PontuacaoHttpConverter;
import br.com.fiap.reciclamais.entrypoints.data.request.pontuacao.RegistroPontuacaoContractRequest;
import br.com.fiap.reciclamais.entrypoints.data.request.pontuacao.TrocaPontuacaoContractRequest;
import br.com.fiap.reciclamais.usecase.data.input.pontuacao.RegistroPontuacaoBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.pontuacao.TrocaPontuacaoBusinessInput;
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

    @Override
    public TrocaPontuacaoBusinessInput toTrocaPontuacaoBusinessInput(TrocaPontuacaoContractRequest request) {
        if (isNull(request))
            return null;

        return TrocaPontuacaoBusinessInput
                .builder()
                .cpf(request.getCpf())
                .voucher(request.getVoucher())
                .build();
    }

    private RegistroPontuacaoBusinessInput toRegistroPontuacaoBusinessInput(RegistroPontuacaoContractRequest pontuacao) {
        return RegistroPontuacaoBusinessInput
                .builder()
                .uuid(pontuacao.getUuid())
                .data(pontuacao.getData())
                .build();
    }

}
