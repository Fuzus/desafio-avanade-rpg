package br.com.fuzus.avanadedesafiorpg.infra.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI configSpringDoc(){
        return new OpenAPI()
                .info(new Info()
                        .title("Avanade Desafio RPG")
                        .description("Batalha RPG no estilo Advanced Dungeons e Dragons")
                );
    }

}
