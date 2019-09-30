package br.com.fiap.reciclamais.entrypoints.converter;

import br.com.fiap.reciclamais.entrypoints.data.request.cadastro.UsuarioCadastroContractRequest;
import br.com.fiap.reciclamais.entrypoints.data.request.login.UsuarioLoginContractRequest;
import br.com.fiap.reciclamais.usecase.data.input.UsuarioBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.UsuarioLoginBusinessInput;

public interface UsuarioHttpConverter {

    UsuarioLoginBusinessInput toUsuarioLoginBusinessInput(UsuarioLoginContractRequest usuarioRequest);

    UsuarioBusinessInput toUsuarioBusinessInput(UsuarioCadastroContractRequest usuarioRequest);

}
