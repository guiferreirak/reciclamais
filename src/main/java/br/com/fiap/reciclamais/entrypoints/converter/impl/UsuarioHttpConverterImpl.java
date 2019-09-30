package br.com.fiap.reciclamais.entrypoints.converter.impl;

import br.com.fiap.reciclamais.entrypoints.converter.EnderecoHttpConverter;
import br.com.fiap.reciclamais.entrypoints.converter.UsuarioHttpConverter;
import br.com.fiap.reciclamais.entrypoints.data.request.cadastro.UsuarioCadastroContractRequest;
import br.com.fiap.reciclamais.entrypoints.data.request.login.UsuarioLoginContractRequest;
import br.com.fiap.reciclamais.usecase.data.input.UsuarioBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.UsuarioLoginBusinessInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class UsuarioHttpConverterImpl implements UsuarioHttpConverter {

    private final EnderecoHttpConverter enderecoConverter;

    @Override
    public UsuarioLoginBusinessInput toUsuarioLoginBusinessInput(UsuarioLoginContractRequest usuarioRequest) {
        if(isNull(usuarioRequest))
            return null;

        return UsuarioLoginBusinessInput
                .builder()
                .cpf(usuarioRequest.getCpf())
                .senha(usuarioRequest.getSenha())
                .build();
    }

    @Override
    public UsuarioBusinessInput toUsuarioBusinessInput(UsuarioCadastroContractRequest usuarioRequest) {
        if(isNull(usuarioRequest))
            return null;

        return UsuarioBusinessInput
                .builder()
                .nome(usuarioRequest.getNome())
                .email(usuarioRequest.getEmail())
                .senha(usuarioRequest.getSenha())
                .cpf(usuarioRequest.getCpf())
                .endereco(enderecoConverter.parseEndereco(usuarioRequest.getCep(), usuarioRequest.getRua(),
                        usuarioRequest.getNumero(), usuarioRequest.getEstado(), usuarioRequest.getCidade()))
                .build();
    }
}
