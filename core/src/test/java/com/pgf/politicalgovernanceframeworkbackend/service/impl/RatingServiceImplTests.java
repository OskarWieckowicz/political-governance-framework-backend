package com.pgf.politicalgovernanceframeworkbackend.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.pgf.politicalgovernanceframeworkbackend.converter.RatingConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.RatingDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.Rating;
import com.pgf.politicalgovernanceframeworkbackend.repository.pgf.RatingRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RatingServiceImplTests {
    @Mock
    private RatingRepository repository;
    @Mock
    private RatingConverter converter;
    @InjectMocks
    private RatingServiceImpl service;

    @Test
    void shouldSuccessfullyUpdateRating() {
        // Given
        RatingDto ratingDto = getRatingDto();
        Rating existingRating = new Rating();
        existingRating.setId(1L);
        existingRating.setUserId(ratingDto.getUserId());
        existingRating.setTaxBeneficiaryId(ratingDto.getTaxBeneficiaryId());
        existingRating.setBillingPeriod(ratingDto.getBillingPeriod());
        existingRating.setValue(4.5f);

        given(repository.findByUserIdAndTaxBeneficiaryIdAndBillingPeriod(
            ratingDto.getUserId(), ratingDto.getTaxBeneficiaryId(), ratingDto.getBillingPeriod()))
            .willReturn(Optional.of(existingRating));

        // When
        service.createOrUpdateRating(ratingDto);

        // Then
        verify(repository, times(1)).save(existingRating);
        assertThat(existingRating.getValue()).isEqualTo(3.0f);
    }

    @Test
    void shouldSuccessfullyCreateRating() {
        // Given
        RatingDto ratingDto = getRatingDto();
        Rating newRating = new Rating();
        newRating.setUserId(ratingDto.getUserId());
        newRating.setTaxBeneficiaryId(ratingDto.getTaxBeneficiaryId());
        newRating.setBillingPeriod(ratingDto.getBillingPeriod());
        newRating.setValue(4.0f);

        given(repository.findByUserIdAndTaxBeneficiaryIdAndBillingPeriod(
            ratingDto.getUserId(), ratingDto.getTaxBeneficiaryId(), ratingDto.getBillingPeriod()))
            .willReturn(Optional.empty());

        given(converter.convertFrom(ratingDto)).willReturn(newRating);

        // When
        service.createOrUpdateRating(ratingDto);

        // Then
        verify(repository, times(1)).save(newRating);
    }

    private RatingDto getRatingDto() {
        return RatingDto.builder()
            .userId("user123")
            .value(3.0f)
            .taxBeneficiaryId(123L)
            .billingPeriod("10/2023")
            .build();
    }
}
