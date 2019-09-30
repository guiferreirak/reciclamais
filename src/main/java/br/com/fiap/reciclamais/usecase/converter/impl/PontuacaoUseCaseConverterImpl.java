package br.com.fiap.reciclamais.usecase.converter.impl;

import br.com.fiap.reciclamais.gateway.repository.data.PontuacaoDocument;
import br.com.fiap.reciclamais.usecase.converter.PontuacaoUseCaseConverter;
import org.springframework.stereotype.Component;

@Component
public class PontuacaoUseCaseConverterImpl implements PontuacaoUseCaseConverter {

    private static final Double VINTE = 20D;
    private static final Float UM = 1.1F;

    @Override
    public PontuacaoDocument parsePontuacao() {
        return PontuacaoDocument
                .builder()
                .reciclagem(VINTE)
                .percentual(UM)
                .build();
    }
}
