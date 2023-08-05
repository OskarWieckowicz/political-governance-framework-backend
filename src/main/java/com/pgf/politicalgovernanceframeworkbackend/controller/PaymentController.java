package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.dto.PaymentDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/payments")
class PaymentController {

    @GetMapping
    Flux<PaymentDto> getPayments() {
        PaymentDto paymentDto1 = PaymentDto.builder()
                .percentage(20)
                .destination("Education")
                .contractAddress("0xadafawwadadwaddaaddad")
                .value(150f)
                .paid(90f)
                .build();
        PaymentDto paymentDto2 = PaymentDto.builder()
                .percentage(30)
                .destination("Health Care")
                .contractAddress("0x314513441242124")
                .value(120f)
                .paid(40f)
                .build();
        return Flux.just(paymentDto1, paymentDto2);
    }
}
