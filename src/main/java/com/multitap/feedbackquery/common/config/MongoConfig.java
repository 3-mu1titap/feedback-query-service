package com.multitap.feedbackquery.common.config;

import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Bean
    public MongoTemplate mongoTemplate() {
        String connectionString = "mongodb+srv://tnbin98:1q2w3e4r@subin.elho5.mongodb.net/member-query?retryWrites=true&w=majority";
        return new MongoTemplate(MongoClients.create(connectionString), "member-query");
    }
}
