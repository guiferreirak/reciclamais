package br.com.fiap.reciclamais.usecase.converter.impl;

import br.com.fiap.reciclamais.gateway.repository.data.HistoricoDocument;
import br.com.fiap.reciclamais.gateway.repository.data.PontuacaoDocument;
import br.com.fiap.reciclamais.gateway.repository.data.UsuarioDocument;
import br.com.fiap.reciclamais.usecase.converter.UsuarioPontuacaoUseCaseConverter;
import br.com.fiap.reciclamais.usecase.data.input.pontuacao.RegistroPontuacaoBusinessInput;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioPontuacaoUseCaseConverterImpl implements UsuarioPontuacaoUseCaseConverter {

    private static final Double VINTE = 20D;
    private static final Double UM = 1.1D;
    private static final Integer ZERO = 0;

    @Override
    public PontuacaoDocument parsePontuacao() {
        return PontuacaoDocument
                .builder()
                .reciclagem(VINTE)
                .total(ZERO)
                .percentual(UM)
                .historico(Collections.emptyList())
                .build();
    }

    @Override
    public PontuacaoDocument parseRegistroPontuacao(RegistroPontuacaoBusinessInput pontuacaoBusiness, UsuarioDocument usuarioDocument, Double percentual, Double pontuacaoTotal) {
        return PontuacaoDocument
                .builder()
                .reciclagem(pontuacaoTotal)
                .total(incrementaPontuacaoTotal(usuarioDocument.getPontuacao().getTotal(), pontuacaoTotal))
                .percentual(percentual)
                .historico(getListHistorico(usuarioDocument.getPontuacao().getHistorico(), pontuacaoBusiness.getData(), pontuacaoTotal))
                .build();
    }

    private Integer incrementaPontuacaoTotal(Integer total, Double pontuacaoTotal) {
            Integer pontuacao = doubleToInteger(pontuacaoTotal);
            return total += pontuacao;
    }

    private List<HistoricoDocument> getListHistorico(List<HistoricoDocument> historicoDocument, LocalDateTime data, Double pontuacaoTotal) {
        historicoDocument.add(HistoricoDocument
                .builder()
                .data(data)
                .ponto(doubleToInteger(pontuacaoTotal))
                .build());

        return historicoDocument;
    }

    private Integer doubleToInteger(Double numero){
        return Math.toIntExact(Math.round(numero));
    }

}
