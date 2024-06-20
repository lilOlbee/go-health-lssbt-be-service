package com.gohealth.lssbt.service;

import com.gohealth.lssbt.service.ui.controller.IssueUIController;
import com.gohealth.lssbt.service.ui.controller.UIController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = LssbtServiceSpringBootApplication.class)
public class LssbtServiceSpringBootApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(LssbtServiceSpringBootApplication.class, args);
  }

  private final UIController UIController;

  @Autowired
  public LssbtServiceSpringBootApplication(IssueUIController UIController) {
    this.UIController = UIController;
  }

  @Override
  public void run(String... args) {
    UIController.run();
  }
}
