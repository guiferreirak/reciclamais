package br.com.fiap.reciclamais.usecase.converter;

import br.com.fiap.reciclamais.gateway.repository.data.PontuacaoDocument;

public interface PontuacaoUseCaseConverter {

    PontuacaoDocument parsePontuacao();
}
