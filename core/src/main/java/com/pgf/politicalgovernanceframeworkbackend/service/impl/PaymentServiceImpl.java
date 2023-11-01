package com.pgf.politicalgovernanceframeworkbackend.service.impl;

import com.pgf.politicalgovernanceframeworkbackend.dto.PaymentDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.PaymentReceivedEventDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDetailsDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxDistributionDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.UserAttributeDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.UserDto;
import com.pgf.politicalgovernanceframeworkbackend.exception.NotFoundException;
import com.pgf.politicalgovernanceframeworkbackend.service.PaymentService;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentReceivedEventServiceImpl paymentReceivedEventService;
    private final TaxBeneficiaryDetailsServiceImpl taxBeneficiaryDetailsService;
    private final UserServiceImpl userService;
    private final TaxesDistributionDeclarationServiceImpl taxesDistributionDeclarationService;

    public List<PaymentDto> findByUserId(String userId) {

        UserDto userDto = userService.findById(userId);

        List<TaxBeneficiaryDetailsDto> allTaxBeneficiariesDetails =
            taxBeneficiaryDetailsService.getAllTaxBeneficiariesDetails();


        List<PaymentDto> payments = new ArrayList<>();

        List<TaxDistributionDto> distributions =
            taxesDistributionDeclarationService.findByUserId(userId).getDistributions();

        for (TaxBeneficiaryDetailsDto taxBeneficiaryDetailsDto : allTaxBeneficiariesDetails) {
            String taxId = getTaxId(userDto);
            List<PaymentReceivedEventDto> events =
                paymentReceivedEventService.findEvents(taxId, taxBeneficiaryDetailsDto.getSmartContractAddress());

            TaxDistributionDto taxDistributionDto = distributions.stream()
                .filter(distribution -> distribution.getDestination().equals(taxBeneficiaryDetailsDto.getName()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Tax distribution not found"));

            BigInteger totalPaymentsAmount = getTotalPaymentsFromEvents(events);
            BigInteger toBePaid = taxDistributionDto.getToBePaid();
            BigInteger leftToPay = calculateLeftToPay(toBePaid, totalPaymentsAmount);
            payments.add(PaymentDto.builder()
                .contractAddress(taxBeneficiaryDetailsDto.getSmartContractAddress())
                .destination(taxBeneficiaryDetailsDto.getName())
                .toBePaid(toBePaid)
                .paid(totalPaymentsAmount)
                .leftToPay(leftToPay)
                .build());
        }
        return payments;
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

    private BigInteger calculateLeftToPay(BigInteger toBePaid, BigInteger paid) {
        int comparisonResult = toBePaid.compareTo(paid);

        if (comparisonResult > 0) {
            return toBePaid.subtract(paid);
        } else {
            return BigInteger.ZERO;
        }
    }
}

