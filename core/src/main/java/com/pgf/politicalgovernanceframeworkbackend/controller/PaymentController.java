package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.dto.PaymentDto;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pgf.politicalgovernanceframeworkbackend.service.PaymentService;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
class PaymentController {
    private final PaymentService paymentService;

    @GetMapping
    List<PaymentDto> getPayments(Principal principal) {
        return this.paymentService.findByUserId(principal.getName());
    }
}
