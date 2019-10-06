package br.com.fiap.reciclamais.usecase;

import br.com.fiap.reciclamais.usecase.data.input.UsuarioBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.UsuarioLoginBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.pontuacao.RegistroPontuacaoBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.HistoricoBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.pontuacao.PontuacaoUsuarioBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.pontuacao.RegistroPontuacaoBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.UsuarioBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.UsuarioLoginBusinessOutput;

import java.util.List;

public interface UsuarioUseCase {

    UsuarioLoginBusinessOutput login(UsuarioLoginBusinessInput usuario) throws Exception;

    UsuarioBusinessOutput cadastrarUsuario(UsuarioBusinessInput usuarioBusiness) throws Exception;

    RegistroPontuacaoBusinessOutput registrarPontuacoes(List<RegistroPontuacaoBusinessInput> pontuacoesBusiness) throws Exception;

    List<HistoricoBusinessOutput> getHistoricoPontuacao(String cpf) throws Exception;

    PontuacaoUsuarioBusinessOutput getPontuacao(String cpf) throws Exception;
}
