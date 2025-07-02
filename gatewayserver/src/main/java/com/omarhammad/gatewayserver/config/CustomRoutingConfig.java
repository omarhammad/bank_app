package com.omarhammad.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRoutingConfig {

    @Bean
    public RouteLocator bankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {

        return routeLocatorBuilder.routes()
                .route("accounts-microservice",p -> p
                        .path("/bank/accounts/**")
                        .filters(f -> f.rewritePath("/bank/accounts/(?<segment>.*)", "/${segment}"))
                        .uri("lb://ACCOUNTS")
                )
                .route("cards-microservice",p -> p
                        .path("/bank/cards/**")
                        .filters(f -> f.rewritePath("/bank/cards/(?<segment>.*)", "/${segment}"))
                        .uri("lb://CARDS")
                )
                .route("loans-microservice",p -> p
                        .path("/bank/loans/**")
                        .filters(f -> f.rewritePath("/bank/loans/(?<segment>.*)", "/${segment}"))
                        .uri("lb://LOANS")

                ).build();
    }
}
