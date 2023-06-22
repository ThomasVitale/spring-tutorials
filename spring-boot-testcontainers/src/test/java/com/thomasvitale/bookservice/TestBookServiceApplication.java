package com.thomasvitale.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestBookServiceApplication {

  @Bean
  @RestartScope
  @ServiceConnection
  PostgreSQLContainer<?> postgreSQLContainer() {
    return new PostgreSQLContainer<>("postgres:15");
  }
  
  public static void main(String[] args) {
    SpringApplication.from(BookServiceApplication::main)
      .with(TestBookServiceApplication.class)
      .run(args);
  }

}
