package com.pgf.politicalgovernanceframeworkbackend;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class PoliticalGovernanceFrameworkBackendApplication implements CommandLineRunner {

	@Value("${spring.datasource.password}")
	private String haslo;
	public static void main(String[] args) {
		SpringApplication.run(PoliticalGovernanceFrameworkBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Log the password when the application starts
		System.out.println("------------------haslo---------------------");
		System.out.println(haslo);
	}

}
