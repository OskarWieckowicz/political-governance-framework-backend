package com.pgf.politicalgovernanceframeworkbackend.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

import com.pgf.politicalgovernanceframeworkbackend.converter.PaymentReceivedEventConverter;
import com.pgf.politicalgovernanceframeworkbackend.dto.PaymentReceivedEventDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.PaymentReceivedEvent;
import com.pgf.politicalgovernanceframeworkbackend.repository.pgf.PaymentReceivedEventRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentReceivedEventServiceImplTests {
    @Mock
    private PaymentReceivedEventRepository repository;
    @Mock
    private PaymentReceivedEventConverter converter;
    @InjectMocks
    private PaymentReceivedEventServiceImpl service;

    @Test
    void shouldSuccessfullyFindEvents() {
        // given
        String taxId = "d1dion1id1n1id";
        String contractAddress = "0x192j132j312n132n31";
        given(repository.findAllByTaxIdentifierAndContractAddress(taxId, contractAddress)).willReturn(List.of(new PaymentReceivedEvent()));
        given(converter.convertTo(any(PaymentReceivedEvent.class))).willReturn(
            PaymentReceivedEventDto.builder().build());

        // when
        List<PaymentReceivedEventDto> result = service.findEvents(taxId, contractAddress);

        // then
        assertThat(result).isNotNull();
    }

}
