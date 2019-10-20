package br.com.fiap.reciclamais.entrypoints.converter;

import br.com.fiap.reciclamais.entrypoints.data.request.pontuacao.RegistroPontuacaoContractRequest;
import br.com.fiap.reciclamais.entrypoints.data.request.pontuacao.TrocaPontuacaoContractRequest;
import br.com.fiap.reciclamais.usecase.data.input.pontuacao.RegistroPontuacaoBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.pontuacao.TrocaPontuacaoBusinessInput;

import java.util.List;

public interface PontuacaoHttpConverter {

    List<RegistroPontuacaoBusinessInput> toListRegistroPontuacaoBusinessInput(List<RegistroPontuacaoContractRequest> pontuacoes);

    TrocaPontuacaoBusinessInput toTrocaPontuacaoBusinessInput(TrocaPontuacaoContractRequest request);
}
