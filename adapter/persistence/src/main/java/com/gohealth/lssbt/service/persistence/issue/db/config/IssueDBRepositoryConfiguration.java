package com.gohealth.lssbt.service.persistence.issue.db.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.TransactionProvider;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.ThreadLocalTransactionProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "local-storage.type", havingValue = "db")
public class IssueDBRepositoryConfiguration {

  @Bean
  public DataSource dataSource(
      @Value("${spring.datasource.url}") String url,
      @Value("${spring.datasource.username}") String username,
      @Value("${spring.datasource.password}") String password,
      @Value("${spring.datasource.hikari.driver-class-name}") String driverClassName,
      @Value("${spring.datasource.hikari.connection-timeout}") String connectionTimeout,
      @Value("${spring.datasource.hikari.maximum-pool-size}") int maxPoolSize) {
    final HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl(url);
    hikariConfig.setUsername(username);
    hikariConfig.setPassword(password);
    hikariConfig.setConnectionTimeout(Long.parseLong(connectionTimeout));
    hikariConfig.setAutoCommit(true);
    hikariConfig.setDriverClassName(driverClassName);
    hikariConfig.setTransactionIsolation("TRANSACTION_READ_COMMITTED");
    hikariConfig.setPoolName("postgres-db");
    hikariConfig.setMaximumPoolSize(maxPoolSize);
    return new HikariDataSource(hikariConfig);
  }

  @Bean
  public TransactionProvider transactionProvider(DataSource dataSource) {
    return new ThreadLocalTransactionProvider(new DataSourceConnectionProvider(dataSource));
  }

  @Bean
  public DSLContext dslContext(DataSource dataSource, TransactionProvider transactionProvider) {
    final DefaultConfiguration config = new DefaultConfiguration();
    config.setDataSource(dataSource);
    config.setSQLDialect(SQLDialect.POSTGRES);
    config.setTransactionProvider(transactionProvider);
    return new DefaultDSLContext(config);
  }
}
