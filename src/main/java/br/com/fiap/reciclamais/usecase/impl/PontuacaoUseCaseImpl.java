package br.com.fiap.reciclamais.usecase.impl;

import br.com.fiap.reciclamais.config.data.ExceptionMessagesConfigurationProperties;
import br.com.fiap.reciclamais.config.data.SucessoMessagesConfigurationProperties;
import br.com.fiap.reciclamais.gateway.repository.UsuarioRepository;
import br.com.fiap.reciclamais.gateway.repository.data.UsuarioDocument;
import br.com.fiap.reciclamais.usecase.PontuacaoUseCase;
import br.com.fiap.reciclamais.usecase.converter.UsuarioUseCaseConverter;
import br.com.fiap.reciclamais.usecase.data.input.pontuacao.RegistroPontuacaoBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.pontuacao.TrocaPontuacaoBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.HistoricoBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.pontuacao.PontuacaoUsuarioBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.pontuacao.RegistroPontuacaoBusinessOutput;
import br.com.fiap.reciclamais.utils.exception.PontuacaoInsuficienteException;
import br.com.fiap.reciclamais.utils.exception.UsuarioInexistenteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static org.springframework.util.ObjectUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class PontuacaoUseCaseImpl implements PontuacaoUseCase {

    private final SucessoMessagesConfigurationProperties sucessoMessageProperties;
    private final ExceptionMessagesConfigurationProperties exceptionMessageProperties;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioUseCaseConverter usuarioConverter;

    @Override
    public RegistroPontuacaoBusinessOutput registrarPontuacoes(List<RegistroPontuacaoBusinessInput> registroPontuacaoBusiness) throws Exception {

        try {

            List<UsuarioDocument> usuarios = registroPontuacaoBusiness.stream().map(this::salvarpontuacao).collect(Collectors.toList());

            if(isEmpty(usuarios)){
                return usuarioConverter.toRegistroPontuacaoBusinessOutput(usuarios);
            }

            return RegistroPontuacaoBusinessOutput
                    .builder()
                    .descricao(sucessoMessageProperties.getPontuacao())
                    .build();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<HistoricoBusinessOutput> getHistoricoPontuacao(String cpf) throws Exception {

        try {
            UsuarioDocument usuarioDocument = usuarioRepository.findByCpf(cpf);

            List<HistoricoBusinessOutput> historicoPontuacao = usuarioConverter.toListHistoricoBusinessOutput(usuarioDocument);

            return historicoPontuacao;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public PontuacaoUsuarioBusinessOutput getPontuacao(String cpf) throws Exception {

        try {
            UsuarioDocument usuarioDocument = usuarioRepository.findByCpf(cpf);

            PontuacaoUsuarioBusinessOutput pontuacaoUsuario = usuarioConverter.toPontuacaoUsuarioBusinessOutput(usuarioDocument);

            return pontuacaoUsuario;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void trocarPontuacao(TrocaPontuacaoBusinessInput pontuacaoBusiness) throws Exception {

        try {
            UsuarioDocument usuarioDocument = usuarioRepository.findByCpf(pontuacaoBusiness.getCpf());

            if (isNull(usuarioDocument))
                throw new UsuarioInexistenteException(exceptionMessageProperties.getUsuarioNaoEncontrado());
            if(!isValidPontuacao(usuarioDocument, pontuacaoBusiness))
                throw new PontuacaoInsuficienteException(exceptionMessageProperties.getPontuacaoInsuficiente());

            Integer pontuacao = (usuarioDocument.getPontuacao().getTotal() - pontuacaoBusiness.getVoucher());

            UsuarioDocument usuario = usuarioConverter.toTrocaPontuacaoUsuarioDocument(usuarioDocument, pontuacao);

            usuarioRepository.save(usuario);

        } catch (UsuarioInexistenteException e){
            throw new UsuarioInexistenteException(e.getMessage());
        } catch (PontuacaoInsuficienteException e) {
            throw new PontuacaoInsuficienteException(e.getMessage());
        } catch (Exception e ) {
            throw new Exception(e.getMessage());
        }
    }

    private boolean isValidPontuacao(UsuarioDocument usuarioDocument, TrocaPontuacaoBusinessInput pontuacaoBusiness) {
        return (usuarioDocument.getPontuacao().getTotal() > pontuacaoBusiness.getVoucher());
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
        } catch (Exception e){
            return usuario;
        }
    }

    private Double calculaPontuacaoTotal(Double pontuacaoReciclagem, Double percentual) {
        return (pontuacaoReciclagem * percentual);
    }
}
