package com.pgf.politicalgovernanceframeworkbackend.fto;

import com.pgf.politicalgovernanceframeworkbackend.dto.AddressDto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProfileFto {
    private String firstName;
    private String lastName;
    private String taxId;
    private String phone;
    private String mail;
    private AddressDto address;
}
