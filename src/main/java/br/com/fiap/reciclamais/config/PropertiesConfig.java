package br.com.fiap.reciclamais.config;

import br.com.fiap.reciclamais.config.data.ErroMessagesConfigurationProperties;
import br.com.fiap.reciclamais.config.data.ExceptionMessagesConfigurationProperties;
import br.com.fiap.reciclamais.config.data.SucessoMessagesConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ExceptionMessagesConfigurationProperties.class, SucessoMessagesConfigurationProperties.class,
                                ErroMessagesConfigurationProperties.class})
public class PropertiesConfig {
}
