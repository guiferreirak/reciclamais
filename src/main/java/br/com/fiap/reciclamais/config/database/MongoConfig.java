//package br.com.fiap.reciclamais.config.database;
//
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientURI;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MongoConfig {
//
//    //private static final String URI = "mongodb+srv://developer:developer@reciclamais-npy5o.mongodb.net/test?retryWrites=true&w=majority";
//    private static final String URI = "mongodb://developer:developer123@ds137488.mlab.com:37488/heroku_qh597p5f?authSource=admin&authMechanism=SCRAM-SHA-1";
//
//    @Bean
//    public MongoClient connectionFactory(){
//        return new MongoClient(getUriConnection());
//    }
//
//    private MongoClientURI getUriConnection() {
//        return new MongoClientURI(URI);
//    }
//
//}
