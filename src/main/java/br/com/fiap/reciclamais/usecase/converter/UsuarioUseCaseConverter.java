package br.com.fiap.reciclamais.usecase.converter;

import br.com.fiap.reciclamais.gateway.repository.data.UsuarioDocument;
import br.com.fiap.reciclamais.usecase.data.input.UsuarioBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.atualiza.UsuarioAtualizaBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.pontuacao.RegistroPontuacaoBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.HistoricoBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.UsuarioBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.login.UsuarioLoginBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.pontuacao.PontuacaoUsuarioBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.pontuacao.RegistroPontuacaoBusinessOutput;

import java.util.List;

public interface UsuarioUseCaseConverter {

    UsuarioLoginBusinessOutput toUsuarioLoginBusinessOutput(UsuarioDocument usuarioDocument);

    UsuarioDocument toUsuarioDocument(UsuarioBusinessInput usuarioBusinessInput);

    UsuarioDocument toRegistroPontuacaoUsuarioDocument(RegistroPontuacaoBusinessInput pontuacaoBusiness, UsuarioDocument usuarioDocument, Double percentual, Double pontuacaoTotal);

    RegistroPontuacaoBusinessOutput toRegistroPontuacaoBusinessOutput(List<UsuarioDocument> usuarios);

    List<HistoricoBusinessOutput> toListHistoricoBusinessOutput(UsuarioDocument usuarioDocument);

    PontuacaoUsuarioBusinessOutput toPontuacaoUsuarioBusinessOutput(UsuarioDocument usuarioDocument);

    UsuarioBusinessOutput toUsuarioBusinessOutput(UsuarioDocument usuarioDocument);

    UsuarioDocument toUsuarioAtualizaDocument(UsuarioDocument usuarioDocument, UsuarioAtualizaBusinessInput usuarioBusinessInput);

    UsuarioDocument toTrocaPontuacaoUsuarioDocument(UsuarioDocument usuarioDocument, Integer pontuacao);
}
