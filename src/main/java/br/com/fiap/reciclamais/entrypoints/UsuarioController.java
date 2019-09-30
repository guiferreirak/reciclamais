package br.com.fiap.reciclamais.entrypoints;

import br.com.fiap.reciclamais.entrypoints.converter.UsuarioHttpConverter;
import br.com.fiap.reciclamais.entrypoints.data.request.cadastro.UsuarioCadastroContractRequest;
import br.com.fiap.reciclamais.entrypoints.data.request.login.UsuarioLoginContractRequest;
import br.com.fiap.reciclamais.usecase.UsuarioUseCase;
import br.com.fiap.reciclamais.usecase.data.input.UsuarioBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.UsuarioLoginBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.UsuarioBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.UsuarioLoginBusinessOutput;
import br.com.fiap.reciclamais.utils.converter.HttpConverterWrapper;
import br.com.fiap.reciclamais.utils.data.HttpContractResponseWrapper;
import br.com.fiap.reciclamais.utils.exception.LoginInvalidoException;
import br.com.fiap.reciclamais.utils.exception.UsuarioExistenteException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioUseCase usuarioUseCase;
    private final UsuarioHttpConverter usuarioConverter;
    private final HttpConverterWrapper httpConverter;

    @PostMapping("login")
    public ResponseEntity<HttpContractResponseWrapper> login(@RequestBody UsuarioLoginContractRequest usuario) {

        try {
            UsuarioLoginBusinessInput usuarioBusinessInput = usuarioConverter.toUsuarioLoginBusinessInput(usuario);

            UsuarioLoginBusinessOutput usuarioBusinessOutput = usuarioUseCase.login(usuarioBusinessInput);

            return ResponseEntity.ok().body(httpConverter.toHttpSuccess(usuarioBusinessOutput));
        } catch (LoginInvalidoException e){
            return ResponseEntity.badRequest().body(httpConverter.toHttpException(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(httpConverter.toHttpException(e.getMessage()));
        }
    }

    @PostMapping("cadastrar")
    public ResponseEntity<HttpContractResponseWrapper> cadastrarUsuario(@RequestBody UsuarioCadastroContractRequest usuario) {

        try {
            UsuarioBusinessInput usuarioBusinessInput = usuarioConverter.toUsuarioBusinessInput(usuario);

            usuarioUseCase.cadastrarUsuario(usuarioBusinessInput);

            return ResponseEntity.ok().body(httpConverter.toHttpSuccess("Usuario cadastrado com sucesso"));
        } catch (UsuarioExistenteException e){
            return ResponseEntity.badRequest().body(httpConverter.toHttpException(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(httpConverter.toHttpException(e.getMessage()));
        }
    }

}
