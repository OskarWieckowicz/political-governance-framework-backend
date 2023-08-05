package com.pgf.politicalgovernanceframeworkbackend.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProfileDto {
    private String name;
    private String lastName;
    private String taxId;
    private String country;
    private String city;
    private String street;
    private String postal;
    private String phone;
    private String mail;
}
