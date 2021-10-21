package com.thomasvitale.greetingservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class GreetingController {

    @Value("${greeting}")
    private String greeting;

    @GetMapping("greeting")
    public String getGreeting() {
        return greeting;
    }

}
