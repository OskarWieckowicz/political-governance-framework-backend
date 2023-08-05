package com.pgf.politicalgovernanceframeworkbackend.controller;

import com.pgf.politicalgovernanceframeworkbackend.service.Web3Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
class TaxOfficeController {

    private final Web3Service web3Service;

    @GetMapping("/logs")
    ResponseEntity<Void> getLogs() throws IOException {
        web3Service.getPaymentReceivedLogs();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
