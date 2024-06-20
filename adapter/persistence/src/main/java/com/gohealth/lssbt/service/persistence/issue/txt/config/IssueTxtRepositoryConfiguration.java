package com.gohealth.lssbt.service.persistence.issue.txt.config;

import java.io.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "local-storage.type", havingValue = "txt")
public class IssueTxtRepositoryConfiguration {

  @Value("${local-storage.txt.file-path}")
  private String filePath;

  @Value("${local-storage.txt.file-name}")
  private String fileName;

  @Bean
  public BufferedWriter bufferedWriter() throws IOException {
    return new BufferedWriter(new FileWriter(filePath + "/" + fileName, true));
  }
}
