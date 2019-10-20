package br.com.fiap.reciclamais.usecase.impl;

import br.com.fiap.reciclamais.config.data.ExceptionMessagesConfigurationProperties;
import br.com.fiap.reciclamais.gateway.repository.UsuarioRepository;
import br.com.fiap.reciclamais.gateway.repository.data.UsuarioDocument;
import br.com.fiap.reciclamais.usecase.UsuarioUseCase;
import br.com.fiap.reciclamais.usecase.converter.UsuarioUseCaseConverter;
import br.com.fiap.reciclamais.usecase.data.input.UsuarioBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.atualiza.UsuarioAtualizaBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.login.UsuarioLoginBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.UsuarioBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.login.UsuarioLoginBusinessOutput;
import br.com.fiap.reciclamais.utils.exception.LoginInvalidoException;
import br.com.fiap.reciclamais.utils.exception.UsuarioExistenteException;
import br.com.fiap.reciclamais.utils.exception.UsuarioInexistenteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class UsuarioUseCaseImpl implements UsuarioUseCase {

    private final ExceptionMessagesConfigurationProperties exceptionMessageProperties;
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

            UsuarioBusinessOutput usuarioBusiness = usuarioConverter.toUsuarioBusinessOutput(usuarioDocument);

            return usuarioBusiness;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public UsuarioBusinessOutput atualizarUsuario(UsuarioAtualizaBusinessInput usuarioBusinessInput) throws Exception {

        try {
            UsuarioDocument usuarioDocument = usuarioRepository.findByCpf(usuarioBusinessInput.getCpf());

            if (isNull(usuarioDocument))
                throw new UsuarioInexistenteException(exceptionMessageProperties.getErroInterno());

            UsuarioDocument usuarioAtualizadoInput = usuarioConverter.toUsuarioAtualizaDocument(usuarioDocument, usuarioBusinessInput);

            usuarioRepository.save(usuarioAtualizadoInput);

            return null;
        } catch (UsuarioInexistenteException e) {
            throw new UsuarioInexistenteException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deletarUsuario(String cpf) throws Exception {

        try{
            usuarioRepository.deleteByCpf(cpf);

        }catch (Exception e ){
            throw new Exception(e.getMessage());
        }
    }

    private void validarLoginUsuario(UsuarioDocument usuarioDocument, UsuarioLoginBusinessInput usuarioBusiness) throws LoginInvalidoException {
        if(isNull(usuarioDocument) || isNull(usuarioDocument.getSenha()) ||
                !usuarioDocument.getSenha().equals(usuarioBusiness.getSenha())) {
            throw new LoginInvalidoException(exceptionMessageProperties.getLoginInvalido());
        }
    }

}
