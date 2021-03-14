package com.thomasvitale.demo;

import reactor.core.publisher.Mono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class SpringNativeGraalvmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringNativeGraalvmApplication.class, args);
	}

	@Bean
	RouterFunction<ServerResponse> routes() {
		return route()
				.GET("/", request -> ok().body(Mono.just("Spring Native and Beyond!"), String.class))
				.build();
	}
}
