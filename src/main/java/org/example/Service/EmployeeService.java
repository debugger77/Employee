package org.example.Service;

import org.example.Model.Employee;
import org.example.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo repo;

    @PostConstruct
    public void initDB() {
        List<Employee> employees = IntStream.rangeClosed(1, 50)
                .mapToObj(i -> new Employee(i, "employee" + i, new Random().nextInt(100), new Random().nextInt(50000)))
                .collect(Collectors.toList());
        repo.saveAll(employees);
    }

    public Flux<Employee> getEmployees() {
        return Flux.fromIterable(repo.findAll());
    }

    public Mono<Employee> getEmployeeById(int id) {
        return Mono.just(repo.findById(id).orElse(new Employee()));
    }

    public void addEmployee(Mono<Employee> emp) {
        System.out.println(2);
        Mono.from(emp).map(repo::save).log();
//        emp.subscribe(System.out::println);
//        int id = emp.map(Employee::getEmployeeId).block();
//        repo.save(Objects.requireNonNull(emp.block()));
    }

    public void addEmployees(List<Employee> list) {
        repo.saveAll(list);
    }

    public void deleteEmployee(int id) {
        repo.deleteById(id);
    }
}



























//    public Mono<EmployeeResponse> getEmployeeAsset(Mono<Employee> tmp, Mono<AssetResponse> atmp) {
//        Employee e = tmp.block();
//        AssetResponse a = atmp.block();
//        EmployeeResponse er = new EmployeeResponse(e.getId(),e.getName(),e.getCity(),e.getSalary(),a);
//        //return Mono.just(er);
//
//        Mono.zip(tmp,atmp);
//    }
