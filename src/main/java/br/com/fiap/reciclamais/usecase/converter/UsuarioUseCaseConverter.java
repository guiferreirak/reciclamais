package br.com.fiap.reciclamais.usecase.converter;

import br.com.fiap.reciclamais.gateway.repository.data.UsuarioDocument;
import br.com.fiap.reciclamais.usecase.data.input.UsuarioBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.UsuarioLoginBusinessOutput;

public interface UsuarioUseCaseConverter {

    UsuarioLoginBusinessOutput toUsuarioLoginBusinessOutput(UsuarioDocument usuarioDocument);

    UsuarioDocument toUsuarioDocument(UsuarioBusinessInput usuarioBusinessInput);
}
