package br.com.fiap.reciclamais.usecase.converter.impl;

import br.com.fiap.reciclamais.gateway.repository.data.UsuarioDocument;
import br.com.fiap.reciclamais.usecase.converter.EnderecoUseCaseConverter;
import br.com.fiap.reciclamais.usecase.converter.PontuacaoUseCaseConverter;
import br.com.fiap.reciclamais.usecase.converter.UsuarioUseCaseConverter;
import br.com.fiap.reciclamais.usecase.data.input.UsuarioBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.UsuarioLoginBusinessOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class UsuarioUseCaseConverterImpl implements UsuarioUseCaseConverter {

    private final EnderecoUseCaseConverter enderecoConverter;
    private final PontuacaoUseCaseConverter pontuacaoConverter;

    @Override
    public UsuarioLoginBusinessOutput toUsuarioLoginBusinessOutput(UsuarioDocument usuarioDocument) {
        if(isNull(usuarioDocument))
            return null;

        return UsuarioLoginBusinessOutput
                .builder()
                .nome(usuarioDocument.getNome())
                .perfil(usuarioDocument.getPerfil())
                .build();
    }

    @Override
    public UsuarioDocument toUsuarioDocument(UsuarioBusinessInput usuarioBusinessInput) {
        if(isNull(usuarioBusinessInput))
            return null;

        return UsuarioDocument
                .builder()
                .uuid(getUuid())
                .nome(usuarioBusinessInput.getNome())
                .email(usuarioBusinessInput.getEmail())
                .cpf(usuarioBusinessInput.getCpf())
                .perfil(usuarioBusinessInput.getPerfil())
                .senha(usuarioBusinessInput.getSenha())
                .endereco(enderecoConverter.parseEndereco(usuarioBusinessInput.getEndereco()))
                .pontuacao(pontuacaoConverter.parsePontuacao())
                .build();
    }

    private String getUuid() {
        return UUID.randomUUID().toString();
    }
}
