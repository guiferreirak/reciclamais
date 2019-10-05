package br.com.fiap.reciclamais.usecase.converter;

import br.com.fiap.reciclamais.gateway.repository.data.UsuarioDocument;
import br.com.fiap.reciclamais.usecase.data.input.UsuarioBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.pontuacao.RegistroPontuacaoBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.UsuarioLoginBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.pontuacao.RegistroPontuacaoBusinessOutput;

import java.util.List;
import java.util.Optional;

public interface UsuarioUseCaseConverter {

    UsuarioLoginBusinessOutput toUsuarioLoginBusinessOutput(UsuarioDocument usuarioDocument);

    UsuarioDocument toUsuarioDocument(UsuarioBusinessInput usuarioBusinessInput);

    UsuarioDocument toRegistroPontuacaoUsuarioDocument(RegistroPontuacaoBusinessInput pontuacaoBusiness, UsuarioDocument usuarioDocument, Double percentual, Double pontuacaoTotal);

    RegistroPontuacaoBusinessOutput toRegistroPontuacaoBusinessOutput(List<UsuarioDocument> usuarios);
}
