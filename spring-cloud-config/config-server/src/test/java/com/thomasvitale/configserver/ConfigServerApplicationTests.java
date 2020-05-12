package com.thomasvitale.configserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConfigServerApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void defaultConfigurationAvailable() {
        ResponseEntity<Environment> entity = restTemplate
                .getForEntity("http://localhost:" + port + "/application/default", Environment.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void greetingServiceConfigurationAvailable() {
        ResponseEntity<Environment> entity = restTemplate
                .getForEntity("http://localhost:" + port + "/application/greeting-service", Environment.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
