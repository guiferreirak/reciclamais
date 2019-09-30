package br.com.fiap.reciclamais.usecase.converter;

import br.com.fiap.reciclamais.gateway.repository.data.EnderecoDocument;
import br.com.fiap.reciclamais.usecase.data.input.EnderecoBusinessInput;

public interface EnderecoUseCaseConverter {

    EnderecoDocument parseEndereco(EnderecoBusinessInput endereco);
}
