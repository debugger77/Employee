package org.example;

import org.example.Configuration.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =SpringApplication.run(Main.class, args);
        Config greetingClient = context.getBean(Config.class);
    }
}