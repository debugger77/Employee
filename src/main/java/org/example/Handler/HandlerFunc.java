package org.example.Handler;

import org.example.Model.AssetResponse;
import org.example.Model.Employee;
import org.example.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class HandlerFunc {

    @Autowired
    EmployeeService service;

    @Autowired
    WebClient webClient;

    public Mono<ServerResponse> getEmployees(ServerRequest serverRequest) {
        System.out.println(1);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getEmployees().log(), Employee.class);
    }

    public Mono<ServerResponse> getEmployeeByID(ServerRequest serverRequest) {
        int Id = Integer.valueOf(serverRequest.pathVariable("id"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getEmployeeById(Id).log(), Employee.class);
    }

    public Mono<ServerResponse> getAsset(ServerRequest serverRequest){
        int Id = Integer.valueOf(serverRequest.pathVariable("id"));
        Mono<AssetResponse> Atmp = webClient.get().uri("/asset/"+Id)
                .retrieve().bodyToMono(AssetResponse.class);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Atmp, AssetResponse.class);
    }

    public Mono<ServerResponse> AddEmployee(ServerRequest serverRequest) {
        Mono<Employee> mono = serverRequest.bodyToMono(Employee.class);

        service.addEmployee(mono);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mono, Employee.class);
    }
}
