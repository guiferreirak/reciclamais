package br.com.fiap.reciclamais.usecase.converter.impl;

import br.com.fiap.reciclamais.gateway.repository.data.HistoricoDocument;
import br.com.fiap.reciclamais.usecase.converter.HistoricoUseCaseConverter;
import br.com.fiap.reciclamais.usecase.data.output.HistoricoBusinessOutput;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class HistoricoUseCaseConverterImpl implements HistoricoUseCaseConverter {

    @Override
    public List<HistoricoBusinessOutput> parseListHistoricoPontuacao(List<HistoricoDocument> historico) {
        if (isNull(historico) || isEmpty(historico))
            return emptyList();

        return historico.stream().map(this::parseHistoricoPontuacao).collect(Collectors.toList());
    }

    private HistoricoBusinessOutput parseHistoricoPontuacao(HistoricoDocument historicoDocument) {
        return HistoricoBusinessOutput
                .builder()
                .data(historicoDocument.getData())
                .ponto(historicoDocument.getPonto())
                .build();
    }
}
