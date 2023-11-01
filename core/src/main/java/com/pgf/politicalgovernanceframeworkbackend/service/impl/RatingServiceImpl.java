package com.pgf.politicalgovernanceframeworkbackend.service.impl;

import com.pgf.politicalgovernanceframeworkbackend.converter.RatingConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.RatingDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.Rating;
import com.pgf.politicalgovernanceframeworkbackend.repository.pgf.RatingRepository;
import com.pgf.politicalgovernanceframeworkbackend.service.RatingService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository repository;
    private final RatingConverter converter;

    public void createOrUpdateRating(RatingDto ratingDto) {
        Optional<Rating> ratingOptional = repository.findByUserIdAndTaxBeneficiaryIdAndBillingPeriod(
            ratingDto.getUserId(),
            ratingDto.getTaxBeneficiaryId(),
            ratingDto.getBillingPeriod()
        );

        if (ratingOptional.isPresent()) {
            Rating existingRating = ratingOptional.get();
            existingRating.setValue(ratingDto.getValue());
            repository.save(existingRating);
        } else {
            Rating newRating = converter.convertFrom(ratingDto);
            repository.save(newRating);
        }
    }
}

