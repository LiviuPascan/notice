package com.springliviu.notice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Marks this class as a configuration bean
public class SwaggerConfig {

    @Bean // Registers OpenAPI bean in Spring context
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("NoteKeeper API") // Title for the API
                        .version("1.0") // API version
                        .description("A simple note-keeping application built with Spring Boot and PostgreSQL.") // Description
                        .contact(new Contact()
                                .name("Your Name")
                                .email("your.email@example.com")
                                .url("https://github.com/your-repo"))); // Contact info
    }
}
