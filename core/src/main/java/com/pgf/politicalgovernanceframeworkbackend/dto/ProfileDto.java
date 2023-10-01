package com.pgf.politicalgovernanceframeworkbackend.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProfileDto {
    private String firstName;
    private String lastName;
    private String taxId;
    private String phone;
    private String mail;
    private AddressDto address;
}
