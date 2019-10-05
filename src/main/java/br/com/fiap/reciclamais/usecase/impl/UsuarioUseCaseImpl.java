package br.com.fiap.reciclamais.usecase.impl;

import br.com.fiap.reciclamais.gateway.repository.UsuarioRepository;
import br.com.fiap.reciclamais.gateway.repository.data.UsuarioDocument;
import br.com.fiap.reciclamais.usecase.UsuarioUseCase;
import br.com.fiap.reciclamais.usecase.converter.UsuarioUseCaseConverter;
import br.com.fiap.reciclamais.usecase.data.input.UsuarioBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.UsuarioLoginBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.pontuacao.RegistroPontuacaoBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.UsuarioBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.UsuarioLoginBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.pontuacao.RegistroPontuacaoBusinessOutput;
import br.com.fiap.reciclamais.utils.exception.LoginInvalidoException;
import br.com.fiap.reciclamais.utils.exception.UsuarioExistenteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.springframework.util.ObjectUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class UsuarioUseCaseImpl implements UsuarioUseCase {

    private static final String LOGIN_INVALIDO = "Usuario ou senha incorreto";
    private static final String CPF_EXISTENTE = "CPF ja possui cadastro";
    private static final String EMAIL_EXISTENTE = "Email ja possui cadastro";
    private static final String PONTUACAO_SUCESSO = "Pontuacoes registradas com sucesso";

    private final UsuarioRepository usuarioRepository;
    private final UsuarioUseCaseConverter usuarioConverter;

    @Override
    public UsuarioLoginBusinessOutput login(UsuarioLoginBusinessInput usuarioBusiness) throws Exception {
        try{

            UsuarioDocument usuarioDocument = usuarioRepository.findByCpf(usuarioBusiness.getCpf());

            validarLoginUsuario(usuarioDocument, usuarioBusiness);

            return usuarioConverter.toUsuarioLoginBusinessOutput(usuarioDocument);
        }catch (LoginInvalidoException e){
            throw new LoginInvalidoException(e.getMessage());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public UsuarioBusinessOutput cadastrarUsuario(UsuarioBusinessInput usuarioBusiness) throws Exception {
        try {

            UsuarioDocument usuarioByCpf = usuarioRepository.findByCpf(usuarioBusiness.getCpf());
            UsuarioDocument usuarioByEmail = usuarioRepository.findByEmail(usuarioBusiness.getEmail());

            if (nonNull(usuarioByCpf))
                throw new UsuarioExistenteException(CPF_EXISTENTE);
            if(nonNull(usuarioByEmail))
                throw new UsuarioExistenteException(EMAIL_EXISTENTE);

            UsuarioDocument usuarioDocument = usuarioConverter.toUsuarioDocument(usuarioBusiness);

            usuarioRepository.save(usuarioDocument);

            return null;
        }catch (UsuarioExistenteException e){
            throw new UsuarioExistenteException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public RegistroPontuacaoBusinessOutput registrarPontuacoes(List<RegistroPontuacaoBusinessInput> registroPontuacaoBusiness) throws Exception {
        try {

            List<UsuarioDocument> usuarios = registroPontuacaoBusiness.stream().map(this::salvarpontuacao).collect(Collectors.toList());

            if(isEmpty(usuarios)){
                return usuarioConverter.toRegistroPontuacaoBusinessOutput(usuarios);
            }

            return RegistroPontuacaoBusinessOutput
                    .builder()
                    .descricao(PONTUACAO_SUCESSO)
                    .build();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private UsuarioDocument salvarpontuacao(RegistroPontuacaoBusinessInput pontuacaoBusiness) {

        UsuarioDocument usuario = null;
        try {

            Optional<UsuarioDocument> usuarioDocument = usuarioRepository.findById(pontuacaoBusiness.getUuid());

            if(usuarioDocument.isPresent()){
                usuario = usuarioDocument.get();
                Double pontuacaoReciclagem = usuarioDocument.get().getPontuacao().getReciclagem();
                Double percentual = usuarioDocument.get().getPontuacao().getPercentual();
                Double pontuacaoTotal = calculaPontuacaoTotal(pontuacaoReciclagem, percentual);
                UsuarioDocument usuarioPontuado =
                        usuarioConverter.toRegistroPontuacaoUsuarioDocument(pontuacaoBusiness, usuarioDocument.get(), percentual, pontuacaoTotal);
                usuarioRepository.save(usuarioPontuado);
                return null;
            }

            return null;
        }catch (Exception e){
            return usuario;
        }
    }

    private Double calculaPontuacaoTotal(Double pontuacaoReciclagem, Double percentual) {
        return (pontuacaoReciclagem * percentual);
    }

    private void validarLoginUsuario(UsuarioDocument usuarioDocument, UsuarioLoginBusinessInput usuarioBusiness) throws LoginInvalidoException {
        if(isNull(usuarioDocument) || isNull(usuarioDocument.getSenha()) ||
                !usuarioDocument.getSenha().equals(usuarioBusiness.getSenha())) {
            throw new LoginInvalidoException(LOGIN_INVALIDO);
        }
    }

}
