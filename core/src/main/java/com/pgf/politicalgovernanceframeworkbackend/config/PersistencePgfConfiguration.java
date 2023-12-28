package com.pgf.politicalgovernanceframeworkbackend.config;

import java.util.Map;
import java.util.Objects;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
@Profile("!test")
@Configuration
@EnableJpaRepositories(
    basePackages = "com.pgf.politicalgovernanceframeworkbackend.repository.pgf",
    entityManagerFactoryRef = "pgfEntityManager",
    transactionManagerRef = "pgfTransactionManager")
@RequiredArgsConstructor
public class PersistencePgfConfiguration {
    private final Environment env;

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean pgfEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(pgfDataSource());
        em.setPackagesToScan("com.pgf.politicalgovernanceframeworkbackend.entity.pgf");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaPropertyMap(
            Map.of(
                "spring.jpa.database-platform",
                Objects.requireNonNull(env.getProperty("spring.jpa.database-platform")),
                "spring.jpa.hibernate.ddl-auto",
                Objects.requireNonNull(env.getProperty("spring.jpa.hibernate.ddl-auto")),
                "hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy",
        "hibernate.implicit_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy"
            )
        );
        return em;
    }

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource pgfDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager pgfTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(pgfEntityManager().getObject());
        return transactionManager;
    }
}
