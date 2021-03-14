package com.thomasvitale.demo;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class SpringNativeGraalvmApplicationTests {

	@Autowired
	private WebTestClient webClient;

	@Test
	void whenGetBooksThenReturn() {
		webClient
				.get().uri("/")
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(String.class).isEqualTo("Spring Native and Beyond!");
	}
}
