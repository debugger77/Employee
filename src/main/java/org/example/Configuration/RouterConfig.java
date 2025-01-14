package org.example.Configuration;

import org.example.Handler.HandlerFunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterConfig {

    @Autowired
    HandlerFunc handlerFunction;

    @Bean
    public RouterFunction<ServerResponse> route() {

        return RouterFunctions
            .route().GET("/hello", request -> ServerResponse.ok().bodyValue("Hello, Webflux")).build()
            .andRoute((GET("/getEmployees").and(accept(MediaType.APPLICATION_JSON))),handlerFunction::getEmployees)
            .andRoute((GET("/getEmployeeById/{id}").and(accept(MediaType.APPLICATION_JSON))),handlerFunction::getEmployeeByID)
            .andRoute((POST("/addEmployee").and(accept(MediaType.APPLICATION_JSON))),handlerFunction::AddEmployee)
            .andRoute((GET("/getAsset/{id}").and(accept(MediaType.APPLICATION_JSON))),handlerFunction::getAsset);
    }
}
