package com.pgf.politicalgovernanceframeworkbackend.converter;

import com.pgf.politicalgovernanceframeworkbackend.dto.RatingDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.Rating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingConverter {
    RatingDto convertTo(Rating rating);
    Rating convertFrom(RatingDto ratingDto);
}
