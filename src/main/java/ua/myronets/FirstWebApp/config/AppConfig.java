package ua.myronets.FirstWebApp.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("ua.myronets.FirstWebApp")
@EnableJpaRepositories("ua.myronets.FirstWebApp")
public class AppConfig {

}
