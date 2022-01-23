package com.example.socketdemo.config;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.socketdemo")
public class MongoConfig {

    @Value("${mongodbTrustStoreLocation}")
    String mongodbTrustStoreLocation;

    @Value("${mongodbTrustStorePassword}")
    String mongodbTrustStorePassword;

    @Bean
    public MongoClient mongoClient() {
        System.setProperty("javax.net.ssl.trustStore", mongodbTrustStoreLocation);
        System.setProperty("javax.net.ssl.trustStorePassword", mongodbTrustStorePassword);
//        System.setProperty ("javax.net.ssl.keyStore","D:\\temp\\cert3.p12");
//        System.setProperty ("javax.net.ssl.keyStorePassword","");
        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        MongoClientOptions options = builder.sslEnabled(true).sslInvalidHostNameAllowed(true).build();
//        return new MongoClient("localhost", 27017);
        return new MongoClient(Collections.singletonList(new ServerAddress("127.0.0.1", 27017)),
                Collections.singletonList(MongoCredential.createCredential("ali", "test", "ali".toCharArray())), options);
    }

    @Bean
    protected String getDatabaseName() {
        return "test";
    }


}
