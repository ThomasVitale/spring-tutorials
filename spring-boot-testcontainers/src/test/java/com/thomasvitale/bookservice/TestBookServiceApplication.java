package com.thomasvitale.bookservice;

import org.springframework.boot.SpringApplication;

public class TestBookServiceApplication {
  
  public static void main(String[] args) {
    SpringApplication.from(BookServiceApplication::main)
      .with(TestEnvironmentConfiguration.class)
      .run(args);
  }

}
