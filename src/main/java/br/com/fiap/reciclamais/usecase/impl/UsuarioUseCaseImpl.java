package br.com.fiap.reciclamais.usecase.impl;

import br.com.fiap.reciclamais.config.data.ErroMessagesConfigurationProperties;
import br.com.fiap.reciclamais.config.data.ExceptionMessagesConfigurationProperties;
import br.com.fiap.reciclamais.config.data.SucessoMessagesConfigurationProperties;
import br.com.fiap.reciclamais.gateway.repository.UsuarioRepository;
import br.com.fiap.reciclamais.gateway.repository.data.UsuarioDocument;
import br.com.fiap.reciclamais.usecase.UsuarioUseCase;
import br.com.fiap.reciclamais.usecase.converter.UsuarioUseCaseConverter;
import br.com.fiap.reciclamais.usecase.data.input.UsuarioBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.atualiza.UsuarioAtualizaBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.login.UsuarioLoginBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.UsuarioBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.login.UsuarioLoginBusinessOutput;
import br.com.fiap.reciclamais.utils.enums.PerfilUsuarioEnum;
import br.com.fiap.reciclamais.utils.exception.LoginInvalidoException;
import br.com.fiap.reciclamais.utils.exception.UsuarioExistenteException;
import br.com.fiap.reciclamais.utils.exception.UsuarioInexistenteException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class UsuarioUseCaseImpl implements UsuarioUseCase {

    private static final String USUARIO_IS_FUNCIONARIO = "Este usuario ja é um Funcionario!";
    private static final String USUARIO_IS_ADMINISTRADOR = "Usuario nao pode ser alterado";
    private static final String USUARIO_UPDATED = "Usuario se tornou um Funcionario";

    private final ExceptionMessagesConfigurationProperties exceptionMessageProperties;
    private final ErroMessagesConfigurationProperties erroMessagesProperties;
    private final SucessoMessagesConfigurationProperties sucessoMessagesProperties;
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
                throw new UsuarioExistenteException(exceptionMessageProperties.getCpfExistente());
            if(nonNull(usuarioByEmail))
                throw new UsuarioExistenteException(exceptionMessageProperties.getEmailExistente());

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
    public UsuarioBusinessOutput getUsuario(String cpf) throws Exception {

        try {
            UsuarioDocument usuarioDocument = usuarioRepository.findByCpf(cpf);

            if (isNull(usuarioDocument))
                throw new UsuarioInexistenteException(exceptionMessageProperties.getUsuarioNaoCadastrado());

            UsuarioBusinessOutput usuarioBusiness = usuarioConverter.toUsuarioBusinessOutput(usuarioDocument);

            return usuarioBusiness;
        } catch (UsuarioInexistenteException e) {
            throw new UsuarioInexistenteException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String atualizarUsuario(UsuarioAtualizaBusinessInput usuarioBusinessInput) throws Exception {

        try {
            UsuarioDocument usuarioDocument = usuarioRepository.findByCpf(usuarioBusinessInput.getCpf());

            if (isNull(usuarioDocument))
                throw new UsuarioInexistenteException(exceptionMessageProperties.getErroInterno());

            UsuarioDocument usuarioAtualizadoInput = usuarioConverter.toUsuarioAtualizaDocument(usuarioDocument, usuarioBusinessInput);

            usuarioRepository.save(usuarioAtualizadoInput);

        } catch (UsuarioInexistenteException e) {
            throw new UsuarioInexistenteException(e.getMessage());
        } catch (Exception e) {
            tratarDuplicateKeyError(e);
        }

        return sucessoMessagesProperties.getUsuarioAtualizado();
    }

    @Override
    public void deletarUsuario(String cpf) throws Exception {

        try{
            usuarioRepository.deleteByCpf(cpf);

        }catch (Exception e ){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String alterarPerfilUsuario(String cpf) throws Exception {
        
        try {
            UsuarioDocument usuarioDocument = usuarioRepository.findByCpf(cpf);

            if (usuarioDocument.getPerfil().getCodigo().equals(PerfilUsuarioEnum.FUNCIONARIO.getCodigo()))
                return USUARIO_IS_FUNCIONARIO;
            if (usuarioDocument.getPerfil().getCodigo().equals(PerfilUsuarioEnum.ADMINISTRADOR.getCodigo()))
                return USUARIO_IS_ADMINISTRADOR;
            
            usuarioRepository.save(usuarioConverter.toAlterarPerfilUsuarioDocument(usuarioDocument));
            
            return USUARIO_UPDATED;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void validarLoginUsuario(UsuarioDocument usuarioDocument, UsuarioLoginBusinessInput usuarioBusiness) throws LoginInvalidoException {
        if(isNull(usuarioDocument) || isNull(usuarioDocument.getSenha()) ||
                !usuarioDocument.getSenha().equals(usuarioBusiness.getSenha())) {
            throw new LoginInvalidoException(exceptionMessageProperties.getLoginInvalido());
        }
    }

    private String tratarDuplicateKeyError(Exception e) throws Exception {
        if (e.getMessage().contains("email"))
            throw new Exception(erroMessagesProperties.getEmailDuplicado());

        throw new Exception(exceptionMessageProperties.getErroInterno());
    }

}
