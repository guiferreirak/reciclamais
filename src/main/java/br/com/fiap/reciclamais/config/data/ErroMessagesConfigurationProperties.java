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
@ConfigurationProperties("messages.erro")
public class ErroMessagesConfigurationProperties {

    @NotNull
    private String pontuacao;
}
