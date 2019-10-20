package br.com.fiap.reciclamais.entrypoints.converter;

import br.com.fiap.reciclamais.entrypoints.data.request.atualiza.UsuarioAtualizaContractRequest;
import br.com.fiap.reciclamais.entrypoints.data.request.cadastro.UsuarioCadastroContractRequest;
import br.com.fiap.reciclamais.entrypoints.data.request.login.UsuarioLoginContractRequest;
import br.com.fiap.reciclamais.entrypoints.data.response.UsuarioContractResponse;
import br.com.fiap.reciclamais.usecase.data.input.UsuarioBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.atualiza.UsuarioAtualizaBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.login.UsuarioLoginBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.UsuarioBusinessOutput;

public interface UsuarioHttpConverter {

    UsuarioLoginBusinessInput toUsuarioLoginBusinessInput(UsuarioLoginContractRequest usuarioRequest);

    UsuarioBusinessInput toUsuarioBusinessInput(UsuarioCadastroContractRequest usuarioRequest);

    UsuarioContractResponse toUsuarioContractResponse(UsuarioBusinessOutput usuarioBusiness);

    UsuarioAtualizaBusinessInput toUsuarioAtualizaBusinessInput(UsuarioAtualizaContractRequest request);
}
