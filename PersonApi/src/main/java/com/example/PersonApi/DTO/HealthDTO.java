package com.example.PersonApi.DTO;

import com.example.PersonApi.Enum.BloodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HealthDTO {

    private Long id;

    private BloodType type;

    private String alergy;

}
