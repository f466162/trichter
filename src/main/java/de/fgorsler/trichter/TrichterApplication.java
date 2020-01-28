package de.fgorsler.trichter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableIntegration
@EnableJdbcRepositories
@EnableScheduling
//@EnableBatchProcessing
public class TrichterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrichterApplication.class, args);
    }

}
