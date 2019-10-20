package br.com.fiap.reciclamais.usecase.converter;

import br.com.fiap.reciclamais.gateway.repository.data.HistoricoDocument;
import br.com.fiap.reciclamais.usecase.data.output.HistoricoBusinessOutput;

import java.util.List;

public interface HistoricoUseCaseConverter {

    List<HistoricoBusinessOutput> parseListHistoricoPontuacao(List<HistoricoDocument> historico);
}
