package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.dto.RatingDto;

public interface RatingService {
    void createOrUpdateRating(RatingDto ratingDto);
}
