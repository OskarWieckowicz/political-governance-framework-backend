package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.dto.PaymentDto;
import java.util.List;

public interface PaymentService {
    List<PaymentDto> findByUserId(String userId);
}
