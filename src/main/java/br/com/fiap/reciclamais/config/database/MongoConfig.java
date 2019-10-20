package br.com.fiap.reciclamais.config.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    private static final String URI = "mongodb+srv://developer:developer@reciclamais-npy5o.mongodb.net/test?retryWrites=true&w=majority";

    @Bean
    public MongoClient connectionFactory(){
        return new MongoClient(getUriConnection());
    }

    private MongoClientURI getUriConnection() {
        return new MongoClientURI(URI);
    }

}
