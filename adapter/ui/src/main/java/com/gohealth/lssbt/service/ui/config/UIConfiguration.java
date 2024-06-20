package com.gohealth.lssbt.service.ui.config;

import java.util.Scanner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UIConfiguration {

  @Bean
  public Scanner scanner() {
    return new Scanner(System.in);
  }
}
