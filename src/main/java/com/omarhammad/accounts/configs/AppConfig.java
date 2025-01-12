package com.omarhammad.accounts.configs;

import com.omarhammad.accounts.utils.phoneNumberValidator.PhoneNumberValidator;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PhoneNumberValidator phoneNumberValidator() {
        return new PhoneNumberValidator();
    }
}
