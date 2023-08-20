package com.pgf.politicalgovernanceframeworkbackend.config;

import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDto;
import com.pgf.politicalgovernanceframeworkbackend.service.TaxBeneficiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final TaxBeneficiaryService beneficiaryService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        populateDatabase();
    }

    private void populateDatabase() {

        if (beneficiaryService.getTaxBeneficiaries().isEmpty()) {
            beneficiaryService.saveBeneficiary(TaxBeneficiaryDto.builder()
                .name("Education")
                .description(
                    "Support public education initiatives and programs aimed at providing quality education to students.")
                .img("/education.jpg")
                .rating(4.5f)
                .build());
            beneficiaryService.saveBeneficiary(TaxBeneficiaryDto.builder()
                .name("Health Car")
                .description(
                    "Contribute to public healthcare services and initiatives that improve access to medical care and promote well-being.")
                .img("/health-care.jpg")
                .rating(3.8f)
                .build());
            beneficiaryService.saveBeneficiary(TaxBeneficiaryDto.builder()
                .name("European Union")
                .description(
                    "Show your support for the European Union and its efforts towards economic integration, peace, and cooperation among member countries.")
                .img("/eu.jpg")
                .rating(1f)
                .build());
            beneficiaryService.saveBeneficiary(TaxBeneficiaryDto.builder()
                .name("Army")
                .description(
                    "Contribute to the defense and security of the nation by supporting the armed forces and their missions.")
                .img("/army.jpg")
                .rating(5f)
                .build());
            beneficiaryService.saveBeneficiary(TaxBeneficiaryDto.builder()
                .name("Ministry of Infrastructure")
                .description(
                    "Support the development and maintenance of national infrastructure projects, such as roads, bridges, and public transportation systems.")
                .img("/infrastructure.jpg")
                .rating(2f)
                .build());
        }
    }
}
