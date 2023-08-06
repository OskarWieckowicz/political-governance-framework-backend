package com.pgf.politicalgovernanceframeworkbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PoliticalGovernanceFrameworkBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoliticalGovernanceFrameworkBackendApplication.class, args);
	}

}
