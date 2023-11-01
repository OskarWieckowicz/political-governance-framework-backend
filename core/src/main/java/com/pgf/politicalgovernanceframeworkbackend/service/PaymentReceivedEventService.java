package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.dto.PaymentReceivedEventDto;
import java.util.List;

public interface PaymentReceivedEventService {
    List<PaymentReceivedEventDto> findEvents(String taxId, String contractAddress);
}
