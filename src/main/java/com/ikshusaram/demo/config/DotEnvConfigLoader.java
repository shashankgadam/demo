package com.ikshusaram.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.cdimascio.dotenv.Dotenv;
@Configuration
public class DotEnvConfigLoader {
    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure().load();
    }
}