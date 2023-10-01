package com.pgf.politicalgovernanceframeworkbackend.config;

import java.util.Map;
import java.util.Objects;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.pgf.politicalgovernanceframeworkbackend.repository.keycloak",
    entityManagerFactoryRef = "keycloakEntityManager",
    transactionManagerRef = "keycloakTransactionManager")
@RequiredArgsConstructor
public class PersistenceKeycloakConfiguration {
    private final Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean keycloakEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(keycloakDataSource());
        em.setPackagesToScan("com.pgf.politicalgovernanceframeworkbackend.entity.keycloak");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaPropertyMap(
            Map.of(
                "spring.jpa.database-platform",
                Objects.requireNonNull(env.getProperty("spring.jpa.database-platform")),
                "spring.jpa.hibernate.ddl-auto",
                Objects.requireNonNull(env.getProperty("spring.jpa.hibernate.ddl-auto")))
        );
        return em;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.second-datasource")
    public DataSource keycloakDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager keycloakTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(keycloakEntityManager().getObject());
        return transactionManager;
    }
}
