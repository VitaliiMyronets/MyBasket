package ua.myronets.FirstWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@Configuration
@EnableJpaRepositories("ua.myronets.FirstWebApp.*")
@ComponentScan(basePackages = { "ua.myronets.FirstWebApp.*" })
@EntityScan("ua.myronets.FirstWebApp.*")

@SpringBootApplication
public class FirstWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstWebAppApplication.class, args);
	}

}
