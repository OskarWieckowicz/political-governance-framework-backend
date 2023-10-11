package com.pgf.politicalgovernanceframeworkbackend.config;

import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDetailsDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDto;
import com.pgf.politicalgovernanceframeworkbackend.service.TaxBeneficiaryDetailsService;
import com.pgf.politicalgovernanceframeworkbackend.service.TaxBeneficiaryService;
import java.math.BigInteger;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final TaxBeneficiaryService beneficiaryService;
    private final TaxBeneficiaryDetailsService beneficiaryDetailsService;

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
                .name("Health Care")
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
                .name("Infrastructure")
                .description(
                    "Support the development and maintenance of national infrastructure projects, such as roads, bridges, and public transportation systems.")
                .img("/infrastructure.jpg")
                .rating(2f)
                .build());
        }

        if (beneficiaryDetailsService.getAllTaxBeneficiariesDetails().isEmpty()) {
            beneficiaryDetailsService.saveTaxBeneficiaryDetails(TaxBeneficiaryDetailsDto.builder()
                .image("/infrastructure.jpg")
                .name("Infrastructure")
                .description("""

                    The Ministry of Infrastructure in Poland is responsible for the development and management of the country's infrastructure, 
                    including roads, railways, aviation, and energy. The funds are allocated for the modernization and construction of roads, 
                    railway lines, ports, and airports, enhancing transportation safety, and developing energy infrastructure. 
                    The ministry also ensures efficient utilization of EU funds and plans investments that positively impact economic growth and citizens' quality of life.
                                        """)
                .site("https://www.gov.pl/web/infrastruktura")
                .leader("Andrzej Adamczyk")
                .smartContractAddress("0x36D0fF48DC819749c89baf8B4114b71Bff89dff0")
                .balance(BigInteger.valueOf(1254514))
                .citizensSatisfaction(4.20f)
                .build());
            beneficiaryDetailsService.saveTaxBeneficiaryDetails(TaxBeneficiaryDetailsDto.builder()
                .image("/army.jpg")
                .name("Army")
                .description("""
                    The Polish military, under the Ministry of National Defense, is tasked with ensuring national security and defense.
                    It encompasses the Army, Air Force, Navy, and Special Forces.
                    Its primary role is to safeguard the sovereignty and territorial integrity of the country.
                    The military allocates funds for training, equipment, and technology upgrades to maintain a modern and effective defense force. 
                    It focuses on enhancing interoperability with NATO and allied forces, participating in joint exercises and missions.
                    Additionally, the military plays a crucial role in disaster response, humanitarian assistance, and international peacekeeping efforts. 
                    It is committed to upholding democratic values and contributing to global security initiatives.
                    """)
                .site("https://www.wojsko-polskie.pl/")
                .leader("Mariusz Błaszczak")
                .smartContractAddress("0x0689bEa3a0c8276F5E0b2f43212eC6E2bfCd1c6d")
                .balance(BigInteger.valueOf(354514))
                .citizensSatisfaction(3.20f)
                .build());
            beneficiaryDetailsService.saveTaxBeneficiaryDetails(TaxBeneficiaryDetailsDto.builder()
                .image("/health-care.jpg")
                .name("Health Care")
                .description("""
                    Explore the Polish healthcare system, 
                    comprised of essential elements like medical practices, nurses, clinics, and hospitals. 
                    Primary care visits cater to consultations, preventive check-ups, and vaccinations. 
                    Specialist assessments lead to referrals for outpatient specialized care (ASC), allowing patient choice. 
                    Hospitalization options extend to NFZ-affiliated hospitals. Pharmacists offer valuable drug information. 
                    This system prioritizes patient choice and comprehensive medical care, promoting societal well-being.
                    """)
                .site("https://pacjent.gov.pl/system-opieki-zdrowotnej")
                .leader("Katarzyna Sójka")
                .smartContractAddress("0x209508E1C83ecAeBBFe62AD7388DbacDddeddDC7")
                .balance(BigInteger.valueOf(354514))
                .citizensSatisfaction(1.20f)
                .build());
            beneficiaryDetailsService.saveTaxBeneficiaryDetails(TaxBeneficiaryDetailsDto.builder()
                .image("/education.jpg")
                .name("Education")
                .description("""
                    Between 2017 and 2023, Poland undertook a comprehensive education reform. 
                    The new structure includes an 8-year primary school, 4 years of general high school, 
                    5 years of technical school, 3 years of first-degree vocational school, 
                    2 years of second-degree vocational school, 
                    3 years of special vocational preparation school, and post-secondary vocational school. 
                    Compulsory schooling applies to children and youth aged 7-15, 
                    while the obligation to receive an education extends until the age of 18. 
                    External exams have become pivotal, 
                    enabling access to higher education or the acquisition of professional diplomas. 
                    The reform aims to align the system with diverse student needs and career paths.
                    """)
                .site("https://www.gov.pl/web/edukacja-i-nauka")
                .leader("Przemysław Czarnek")
                .smartContractAddress("0xf3828C26A864C49775E2731a369062Acc19a4EDB")
                .balance(BigInteger.valueOf(124514))
                .citizensSatisfaction(3.94f)
                .build());

            beneficiaryDetailsService.saveTaxBeneficiaryDetails(TaxBeneficiaryDetailsDto.builder()
                .image("/eu.jpg")
                .name("European Union")
                .description("""
                    The main and central goal of EU development cooperation is the
                    eradication of poverty in the context of sustainable development,
                    including the pursuit of the 2030 Agenda for Sustainable Development
                    and its Sustainable Development Goals (SDGs) adopted in 2015 by the
                    United Nations. The EU, together with its Member States, is the
                    largest donor of Official Development Assistance (ODA) in the
                    world. In 2021, the Community together with the Member States
                    (Team Europe) financed over 4,355% of aid on a global scale. The
                    general budget and the European Development Fund (EDF) finance
                    approximately 20% of EU expenditure on development assistance. The
                    rest are Member States' initiatives implemented under national aid
                    schemes. The EU institutions and Member States have committed to
                    jointly achieving an ODA/GNI ratio of 0.7% by 2030."""
                )
                .site("https://european-union.europa.eu/")
                .leader("Ursula von der Leyen")
                .smartContractAddress("0x9D6683Cd4F0783c161a7B705A1af04994B5fF05D")
                .balance(BigInteger.valueOf(124414))
                .citizensSatisfaction(2.94f)
                .build());
        }

    }
}
