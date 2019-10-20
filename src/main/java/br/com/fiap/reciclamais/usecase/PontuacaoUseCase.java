package br.com.fiap.reciclamais.usecase;

import br.com.fiap.reciclamais.usecase.data.input.pontuacao.RegistroPontuacaoBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.pontuacao.TrocaPontuacaoBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.HistoricoBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.pontuacao.PontuacaoUsuarioBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.pontuacao.RegistroPontuacaoBusinessOutput;
import br.com.fiap.reciclamais.utils.exception.PontuacaoInsuficienteException;
import br.com.fiap.reciclamais.utils.exception.UsuarioInexistenteException;

import java.util.List;

public interface PontuacaoUseCase {

    RegistroPontuacaoBusinessOutput registrarPontuacoes(List<RegistroPontuacaoBusinessInput> pontuacoesBusiness) throws Exception;

    List<HistoricoBusinessOutput> getHistoricoPontuacao(String cpf) throws Exception;

    PontuacaoUsuarioBusinessOutput getPontuacao(String cpf) throws Exception;

    void trocarPontuacao(TrocaPontuacaoBusinessInput pontuacaoBusiness) throws Exception;
}
