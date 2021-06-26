package com.thomasvitale.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

@RestController
class GreetingController {

    @GetMapping("/")
    public String getGreeting() {
        return "Welcome to Spring Boot on HTTPS!";
    }
}
