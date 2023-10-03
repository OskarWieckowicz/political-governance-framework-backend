package com.pgf.politicalgovernanceframeworkbackend.converter;

import com.pgf.politicalgovernanceframeworkbackend.dto.AddressDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.UserAttributeDto;
import com.pgf.politicalgovernanceframeworkbackend.dto.UserDto;
import com.pgf.politicalgovernanceframeworkbackend.fto.ProfileFto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserToProfileConverter {

    @Mapping(source = "email", target = "mail")
    @Mapping(source = "attributes", target = "taxId", qualifiedByName = "mapUserAttributeToTaxId")
    @Mapping(source = "attributes", target = "phone", qualifiedByName = "mapUserAttributeToPhone")
    @Mapping(source = "attributes", target = "address", qualifiedByName = "mapUserAttributeToAddress")
    ProfileFto userDtoToProfileFto(UserDto userDto);

    @Named("mapUserAttributeToTaxId")
    default String mapUserAttributeToTaxId(List<UserAttributeDto> attributes) {
        return extractValueByName(attributes, "taxId");
    }

    @Named("mapUserAttributeToPhone")
    default String mapUserAttributeToPhone(List<UserAttributeDto> attributes) {
        return extractValueByName(attributes, "mobile");
    }

    @Named("mapUserAttributeToAddress")
    default AddressDto mapUserAttributeToAddress(List<UserAttributeDto> attributes) {
        return AddressDto.builder()
            .country(extractValueByName(attributes, "country"))
            .city(extractValueByName(attributes, "city"))
            .postalCode(extractValueByName(attributes, "postalCode"))
            .street(extractValueByName(attributes, "street"))
            .build();
    }

    private String extractValueByName(List<UserAttributeDto> attributes, String name) {
        return attributes.stream()
            .filter(userAttributeDto -> name.equals(userAttributeDto.getName()))
            .findFirst()
            .map(UserAttributeDto::getValue)
            .orElse(null);
    }
}
