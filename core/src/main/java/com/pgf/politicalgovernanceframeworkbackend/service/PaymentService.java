package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.dto.DeclarationDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.PaymentDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.PaymentReceivedEventDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDetailsDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxDistributionDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxesDistributionDeclarationDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.UserAttributeDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.UserDto;
import com.pgf.politicalgovernanceframeworkbackend.exception.NotFoundException;
import com.pgf.politicalgovernanceframeworkbackend.utils.EthereumUtils;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    // TODO: integration tests
    private final PaymentReceivedEventService paymentReceivedEventService;
    private final TaxBeneficiaryDetailsService taxBeneficiaryDetailsService;
    private final UserService userService;
    private final DeclarationService declarationService;
    private final TaxesDistributionDeclarationService taxesDistributionDeclarationService;
    private final CryptoPriceService cryptoPriceService;

    public List<PaymentDto> findByUserId(String userId) {
//        PaymentDto paymentDto1 = PaymentDto.builder()
//            .percentage(20)
//            .destination("Education")
//            .contractAddress("0xadafawwadadwaddaaddad")
//            .value(150f)
//            .paid(90f)
//            .build();
//        PaymentDto paymentDto2 = PaymentDto.builder()
//            .percentage(30)
//            .destination("Health Care")
//            .contractAddress("0x314513441242124")
//            .value(120f)
//            .paid(40f)
//            .build();
//        return List.of(paymentDto1, paymentDto2);

        UserDto userDto = userService.findById(userId);

        List<TaxBeneficiaryDetailsDto> allTaxBeneficiariesDetails =
            taxBeneficiaryDetailsService.getAllTaxBeneficiariesDetails();

        TaxesDistributionDeclarationDto taxesDistributionDeclarationDto =
            taxesDistributionDeclarationService.findByUserId(userId);

        DeclarationDto currentDeclaration = declarationService.getCurrentDeclaration(userId);

        List<PaymentDto> payments = new ArrayList<>();

        for (TaxBeneficiaryDetailsDto taxBeneficiaryDetailsDto : allTaxBeneficiariesDetails) {
            String taxId = getTaxId(userDto);
            List<PaymentReceivedEventDto> events =
                paymentReceivedEventService.findEvents(taxId, taxBeneficiaryDetailsDto.getSmartContractAddress());
            BigInteger totalPaymentsAmount = getTotalPaymentsFromEvents(events);
            payments.add(PaymentDto.builder()
                .contractAddress(taxBeneficiaryDetailsDto.getSmartContractAddress())
                .destination(taxBeneficiaryDetailsDto.getName())
                .toBePaid(
                    getToBePaidValue(
                        currentDeclaration.getTaxes(),
                        taxesDistributionDeclarationDto,
                        taxBeneficiaryDetailsDto.getName()
                    )
                )
                .paid(totalPaymentsAmount)
                .build());
        }
        return payments;
    }

    private BigInteger getToBePaidValue(float taxes,
                                   TaxesDistributionDeclarationDto taxesDistributionDeclarationDto,
                                   String nameOfTheBeneficiary) {
        double percentage = getPercentage(taxesDistributionDeclarationDto, nameOfTheBeneficiary).doubleValue();
        double plnValue =  percentage / 100 * taxes;
        double ethPrice = cryptoPriceService.getEthPrice().getPrice();
        double ethValue = ethPrice == 0 ? 0 : plnValue / ethPrice;
        return EthereumUtils.ethToWei(ethValue);
    }

    private static Integer getPercentage(TaxesDistributionDeclarationDto taxesDistributionDeclarationDto,
                                         String nameOfTheBeneficiary) {
        return taxesDistributionDeclarationDto.getDistributions().stream()
            .filter(taxDistributionDto -> taxDistributionDto.getDestination().equals(nameOfTheBeneficiary)).findFirst()
            .map(TaxDistributionDto::getPercentage).orElseThrow(() -> new NotFoundException("Percentage not found!"));
    }


    private String getTaxId(UserDto userDto) {
        return userDto.getAttributes().stream()
            .filter(userAttributeDto -> "taxId".equals(userAttributeDto.getName()))
            .findFirst()
            .map(UserAttributeDto::getValue).orElseThrow(() -> new NotFoundException("Tax id not found"));
    }

    private BigInteger getTotalPaymentsFromEvents(List<PaymentReceivedEventDto> events) {
        BigInteger totalPayments = BigInteger.ZERO;
        for (PaymentReceivedEventDto event : events) {
            if (event.getAmount() != null) {
                totalPayments = totalPayments.add(event.getAmount());
            }
        }
        return totalPayments;
    }
}
