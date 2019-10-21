package br.com.fiap.reciclamais.config.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
@ConfigurationProperties("messages.exception")
public class ExceptionMessagesConfigurationProperties {

    @NotNull
    private String loginInvalido;

    @NotNull
    private String cpfExistente;

    @NotNull
    private String emailExistente;

    @NotNull
    private String erroInterno;

    @NotNull
    private String usuarioNaoEncontrado;

    @NotNull
    private String pontuacaoInsuficiente;

    @NotNull
    private String usuarioNaoCadastrado;
}

