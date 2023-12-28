package com.pgf.politicalgovernanceframeworkbackend.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class ContainersConfiguration {
    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgreSQLContainer(){
        return new PostgreSQLContainer<>("postgres:15-alpine")
            .withUsername("testUser")
            .withPassword("testSecret")
            .withDatabaseName("testDatabase");
    }
}
