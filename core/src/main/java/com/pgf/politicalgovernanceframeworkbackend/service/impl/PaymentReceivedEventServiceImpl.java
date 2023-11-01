package com.pgf.politicalgovernanceframeworkbackend.service.impl;

import com.pgf.politicalgovernanceframeworkbackend.converter.PaymentReceivedEventConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.PaymentReceivedEventDto;
import com.pgf.politicalgovernanceframeworkbackend.repository.pgf.PaymentReceivedEventRepository;
import com.pgf.politicalgovernanceframeworkbackend.service.PaymentReceivedEventService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentReceivedEventServiceImpl implements PaymentReceivedEventService {
    private final PaymentReceivedEventRepository repository;
    private final PaymentReceivedEventConverter converter;

    public List<PaymentReceivedEventDto> findEvents(String taxId, String contractAddress) {
        return repository.findAllByTaxIdentifierAndContractAddress(taxId, contractAddress).stream()
            .map(converter::convertTo).toList();
    }
}
