package ru.ditchsound.catalog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class SwaggerConfig {
    /**
     * Настрйока swagger
     * @return объект настройки OpenAPI
     */
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pul Swagger API"));
    }
}
