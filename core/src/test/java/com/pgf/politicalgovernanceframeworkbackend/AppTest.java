package com.pgf.politicalgovernanceframeworkbackend;

import com.pgf.politicalgovernanceframeworkbackend.config.ContainersConfiguration;
import org.springframework.boot.SpringApplication;

public class AppTest {

    public static void main(String[] args) {

        SpringApplication.from(PoliticalGovernanceFrameworkBackendApplication::main)
            .with(ContainersConfiguration.class)
            .run(args);
    }
}
