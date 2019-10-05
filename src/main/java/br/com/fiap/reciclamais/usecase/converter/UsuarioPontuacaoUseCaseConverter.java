package br.com.fiap.reciclamais.usecase.converter;

import br.com.fiap.reciclamais.gateway.repository.data.PontuacaoDocument;
import br.com.fiap.reciclamais.gateway.repository.data.UsuarioDocument;
import br.com.fiap.reciclamais.usecase.data.input.pontuacao.RegistroPontuacaoBusinessInput;

public interface UsuarioPontuacaoUseCaseConverter {

    PontuacaoDocument parsePontuacao();

    PontuacaoDocument parseRegistroPontuacao(RegistroPontuacaoBusinessInput pontuacaoBusiness,
                                             UsuarioDocument usuarioDocument,
                                             Double percentual,
                                             Double pontuacaoTotal);
}
