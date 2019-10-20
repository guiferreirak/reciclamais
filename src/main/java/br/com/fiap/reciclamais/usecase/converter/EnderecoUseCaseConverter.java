package br.com.fiap.reciclamais.usecase.converter;

import br.com.fiap.reciclamais.gateway.repository.data.EnderecoDocument;
import br.com.fiap.reciclamais.gateway.repository.data.UsuarioDocument;
import br.com.fiap.reciclamais.usecase.data.input.EnderecoBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.EnderecoBusinessOutput;

public interface EnderecoUseCaseConverter {

    EnderecoDocument parseEndereco(EnderecoBusinessInput endereco);

    EnderecoDocument parseEndereco(EnderecoDocument endereco);

    EnderecoBusinessOutput parseToEnderecoBusinessOutput(UsuarioDocument usuarioDocument);
}
