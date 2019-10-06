package br.com.fiap.reciclamais.entrypoints.converter;

import br.com.fiap.reciclamais.entrypoints.data.request.pontuacao.RegistroPontuacaoContractRequest;
import br.com.fiap.reciclamais.usecase.data.input.pontuacao.RegistroPontuacaoBusinessInput;

import java.util.List;

public interface PontuacaoHttpConverter {

    List<RegistroPontuacaoBusinessInput> toListRegistroPontuacaoBusinessInput(List<RegistroPontuacaoContractRequest> pontuacoes);
}
