package br.com.fiap.reciclamais.usecase.converter.impl;

import br.com.fiap.reciclamais.gateway.repository.data.HistoricoDocument;
import br.com.fiap.reciclamais.gateway.repository.data.PontuacaoDocument;
import br.com.fiap.reciclamais.gateway.repository.data.UsuarioDocument;
import br.com.fiap.reciclamais.usecase.converter.HistoricoUseCaseConverter;
import br.com.fiap.reciclamais.usecase.converter.UsuarioPontuacaoUseCaseConverter;
import br.com.fiap.reciclamais.usecase.data.input.pontuacao.RegistroPontuacaoBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.HistoricoBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.PontuacaoBusinessOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
import static org.springframework.util.ObjectUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class UsuarioPontuacaoUseCaseConverterImpl implements UsuarioPontuacaoUseCaseConverter {

    private static final Double VINTE = 20D;
    private static final Double UM = 1.1D;
    private static final Integer ZERO = 0;

    private final HistoricoUseCaseConverter historicoConverter;

    @Override
    public PontuacaoDocument parsePontuacao() {
        return PontuacaoDocument
                .builder()
                .reciclagem(VINTE)
                .total(ZERO)
                .percentual(UM)
                .historico(emptyList())
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

    @Override
    public PontuacaoBusinessOutput parseToPontuacaoBusinessOutput(UsuarioDocument usuarioDocument) {
        if (isNull(usuarioDocument.getPontuacao()))
            return null;

        return PontuacaoBusinessOutput
                .builder()
                .reciclagem(usuarioDocument.getPontuacao().getReciclagem())
                .total(usuarioDocument.getPontuacao().getTotal())
                .percentual(usuarioDocument.getPontuacao().getPercentual())
                .historico(historicoConverter.parseListHistoricoPontuacao(usuarioDocument.getPontuacao().getHistorico()))
                .build();
    }

    @Override
    public PontuacaoDocument parseTrocaPontuacao(Integer pontuacao) {
        return PontuacaoDocument
                .builder()
                .reciclagem(VINTE)
                .total(pontuacao)
                .percentual(UM)
                .historico(emptyList())
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
