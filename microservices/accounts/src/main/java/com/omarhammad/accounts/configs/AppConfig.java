package com.omarhammad.accounts.configs;

import com.omarhammad.accounts.controllers.dtos.AccountsContactInfoDTO;
import com.omarhammad.accounts.utils.audit.AuditAwareSvc;
import com.omarhammad.accounts.utils.phoneNumberValidator.PhoneNumberValidator;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableConfigurationProperties(value = {AccountsContactInfoDTO.class})
@EnableJpaAuditing(auditorAwareRef = "auditAwareSvc")
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PhoneNumberValidator phoneNumberValidator() {
        return new PhoneNumberValidator();
    }

    @Bean
    public AuditAwareSvc auditAwareSvc() {
        return new AuditAwareSvc();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Accounts API Documentation")
                        .version("1.0")
                        .description("Accounts microservice REST API Documentation")
                        .contact(new Contact()
                                .name("Omar Hammad")
                                .email("omar.hammad@gamil.com")
                                .url("https://omarhammad.profile"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://eazybank.com"))
                );

    }
}
