package com.omarhammad.gatewayserver.config;

import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class CustomRoutingConfig {

    @Bean
    public RouteLocator bankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {

        return routeLocatorBuilder.routes()
                .route("accounts-microservice", p -> p
                        .path("/bank/accounts/**")
                        .filters(f -> f
                                .rewritePath("/bank/accounts/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        )
                        .uri("lb://ACCOUNTS")
                )
                .route("cards-microservice", p -> p
                        .path("/bank/cards/**")
                        .filters(f -> f
                                .rewritePath("/bank/cards/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        )
                        .uri("lb://CARDS")
                )
                .route("loans-microservice", p -> p
                        .path("/bank/loans/**")
                        .filters(f -> f
                                .rewritePath("/bank/loans/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        )
                        .uri("lb://LOANS")

                ).build();
    }
}
