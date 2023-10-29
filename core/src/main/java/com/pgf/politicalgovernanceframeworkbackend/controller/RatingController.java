package com.pgf.politicalgovernanceframeworkbackend.controller;

import static com.pgf.politicalgovernanceframeworkbackend.utils.DateUtils.getCurrentBillingPeriod;

import com.pgf.politicalgovernanceframeworkbackend.dto.RatingDto;
import com.pgf.politicalgovernanceframeworkbackend.fto.RatingFto;
import com.pgf.politicalgovernanceframeworkbackend.service.RatingService;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ratings")
class RatingController {
    private final RatingService service;

    @PutMapping()
    ResponseEntity<Void> createOrUpdateRating(Principal principal, @Valid @RequestBody RatingFto ratingFto) {
        RatingDto ratingDto = RatingDto.builder()
            .userId(principal.getName())
            .billingPeriod(getCurrentBillingPeriod())
            .taxBeneficiaryId(ratingFto.getTaxBeneficiaryId())
            .value(ratingFto.getValue())
            .build();
        service.createOrUpdateRating(ratingDto);
        return ResponseEntity.noContent().build();
    }

}
