package br.com.fuzus.avanadedesafiorpg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:messages.properties")
public class AvanadeDesafioRpgApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvanadeDesafioRpgApplication.class, args);
	}

}
