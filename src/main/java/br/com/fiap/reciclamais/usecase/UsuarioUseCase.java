package br.com.fiap.reciclamais.usecase;

import br.com.fiap.reciclamais.usecase.data.input.UsuarioBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.atualiza.UsuarioAtualizaBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.login.UsuarioLoginBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.UsuarioBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.login.UsuarioLoginBusinessOutput;

public interface UsuarioUseCase {

    UsuarioLoginBusinessOutput login(UsuarioLoginBusinessInput usuario) throws Exception;

    UsuarioBusinessOutput cadastrarUsuario(UsuarioBusinessInput usuarioBusiness) throws Exception;

    UsuarioBusinessOutput getUsuario(String cpf) throws Exception;

    UsuarioBusinessOutput atualizarUsuario(UsuarioAtualizaBusinessInput usuarioBusinessInput) throws Exception;

    void deletarUsuario(String cpf) throws Exception;
}
