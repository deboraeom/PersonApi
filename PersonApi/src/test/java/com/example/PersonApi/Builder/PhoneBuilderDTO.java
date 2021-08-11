package com.example.PersonApi.Builder;

import com.example.PersonApi.DTO.PhoneDTO;
import com.example.PersonApi.Enum.PhoneType;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Builder
public class PhoneBuilderDTO {
    @Builder.Default
    private Long id= 1L;

    @Builder.Default
    private PhoneType type=PhoneType.COMPANYPHONE;

    @Builder.Default
    private String number="2232-2233";

    public PhoneDTO phoneDTO(){
        return new PhoneDTO(id,
                           type,
                           number);
    }

}
