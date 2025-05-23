package com.omarhammad.loans.configs;

import com.omarhammad.loans.controllers.dtos.LoansContactInfoDTO;
import com.omarhammad.loans.utils.audit.AuditAwareSvc;
import com.omarhammad.loans.utils.phoneNumberValidator.PhoneNumberValidator;
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
@EnableConfigurationProperties(value = {LoansContactInfoDTO.class})
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
                        .title("Loans API Documentation")
                        .version("1.0")
                        .description("Loans microservice REST API Documentation")
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
