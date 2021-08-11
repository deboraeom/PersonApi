package com.example.PersonApi.Builder;

import com.example.PersonApi.DTO.HealthDTO;
import com.example.PersonApi.Enum.BloodType;
import lombok.Builder;

@Builder
public class HealthBuilderDTO {
    @Builder.Default
    private Long id=1L;
    @Builder.Default
    private BloodType type=BloodType.ABNEGATIVO;
    @Builder.Default
    private String alergy ="Sem alergias";

    public HealthDTO healthDTO(){
        return new HealthDTO(id,type,alergy);
    }

}
