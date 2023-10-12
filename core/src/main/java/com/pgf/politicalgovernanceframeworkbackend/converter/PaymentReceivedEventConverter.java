package com.pgf.politicalgovernanceframeworkbackend.converter;

import com.pgf.politicalgovernanceframeworkbackend.dto.PaymentReceivedEventDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.PaymentReceivedEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentReceivedEventConverter {
    PaymentReceivedEventDto convertTo(PaymentReceivedEvent source);
}
