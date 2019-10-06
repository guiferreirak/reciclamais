package br.com.fiap.reciclamais.usecase.converter.impl;

import br.com.fiap.reciclamais.gateway.repository.data.HistoricoDocument;
import br.com.fiap.reciclamais.gateway.repository.data.PontuacaoDocument;
import br.com.fiap.reciclamais.gateway.repository.data.UsuarioDocument;
import br.com.fiap.reciclamais.usecase.converter.EnderecoUseCaseConverter;
import br.com.fiap.reciclamais.usecase.converter.UsuarioPontuacaoUseCaseConverter;
import br.com.fiap.reciclamais.usecase.converter.UsuarioUseCaseConverter;
import br.com.fiap.reciclamais.usecase.data.input.UsuarioBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.pontuacao.RegistroPontuacaoBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.HistoricoBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.UsuarioLoginBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.pontuacao.PontuacaoUsuarioBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.pontuacao.RegistroPontuacaoBusinessOutput;
import br.com.fiap.reciclamais.utils.enums.PerfilUsuarioEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static org.springframework.util.ObjectUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class UsuarioUseCaseConverterImpl implements UsuarioUseCaseConverter {

    private static final String PONTUACAO_FALHA = "Pontuações registradas com falhas";
    private static final Integer ZERO = 0;

    private final EnderecoUseCaseConverter enderecoConverter;
    private final UsuarioPontuacaoUseCaseConverter pontuacaoConverter;

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
                .perfil(PerfilUsuarioEnum.CLIENTE)
                .senha(usuarioBusinessInput.getSenha())
                .endereco(enderecoConverter.parseEndereco(usuarioBusinessInput.getEndereco()))
                .pontuacao(pontuacaoConverter.parsePontuacao())
                .build();
    }

    @Override
    public UsuarioDocument toRegistroPontuacaoUsuarioDocument(RegistroPontuacaoBusinessInput pontuacaoBusiness,
                                                              UsuarioDocument usuarioDocument,
                                                              Double percentual,
                                                              Double pontuacaoTotal) {
        return UsuarioDocument
                .builder()
                .uuid(usuarioDocument.getUuid())
                .nome(usuarioDocument.getNome())
                .email(usuarioDocument.getEmail())
                .cpf(usuarioDocument.getCpf())
                .perfil(usuarioDocument.getPerfil())
                .senha(usuarioDocument.getSenha())
                .endereco(enderecoConverter.parseEndereco(usuarioDocument.getEndereco()))
                .pontuacao(pontuacaoConverter.parseRegistroPontuacao(pontuacaoBusiness, usuarioDocument, percentual, pontuacaoTotal))
                .build();
    }

    @Override
    public RegistroPontuacaoBusinessOutput toRegistroPontuacaoBusinessOutput(List<UsuarioDocument> usuarios) {
        if(isNull(usuarios) || isEmpty(usuarios))
            return null;

        List<String> uuids = usuarios.stream().map(this::parseUuids).collect(Collectors.toList());

        return RegistroPontuacaoBusinessOutput
                .builder()
                .usuarios(uuids)
                .descricao(PONTUACAO_FALHA)
                .build();
    }

    @Override
    public List<HistoricoBusinessOutput> toListHistoricoBusinessOutput(UsuarioDocument usuarioDocument) {
        if(isNull(usuarioDocument) || isNull(usuarioDocument.getPontuacao())
                || isNull(usuarioDocument.getPontuacao().getHistorico()))
            return Collections.emptyList();

        List<HistoricoDocument> historicoDocument = usuarioDocument.getPontuacao().getHistorico();

        return historicoDocument.stream().map(this::parseToHistoricoBusiness).collect(Collectors.toList());
    }

    @Override
    public PontuacaoUsuarioBusinessOutput toPontuacaoUsuarioBusinessOutput(UsuarioDocument usuarioDocument) {
        if (isNull(usuarioDocument))
            return null;

        return PontuacaoUsuarioBusinessOutput
                .builder()
                .cpf(usuarioDocument.getCpf())
                .nome(usuarioDocument.getNome())
                .pontuacaoTotal(getPontuacaoTotal(usuarioDocument.getPontuacao()))
                .reciclagemTotal(getReciclagemTotal(usuarioDocument.getPontuacao()))
                .build();
    }

    private HistoricoBusinessOutput parseToHistoricoBusiness(HistoricoDocument historicoDocument) {
        if(isNull(historicoDocument))
            return null;

        return HistoricoBusinessOutput
                .builder()
                .data(historicoDocument.getData())
                .ponto(historicoDocument.getPonto())
                .build();
    }

    private Integer getPontuacaoTotal(PontuacaoDocument pontuacao) {
        if (isNull(pontuacao))
            return ZERO;

        return pontuacao.getTotal();
    }

    private Integer getReciclagemTotal(PontuacaoDocument pontuacao) {
        if (isNull(pontuacao))
            return ZERO;

        if (isEmpty(pontuacao.getHistorico()))
            return ZERO;

        return pontuacao.getHistorico().size();
    }

    private String parseUuids(UsuarioDocument usuarioDocument) {
        return usuarioDocument.getUuid();
    }

    private String getUuid() {
        return UUID.randomUUID().toString();
    }
}
