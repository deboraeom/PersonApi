package com.example.PersonApi.Builder;

import com.example.PersonApi.DTO.HealthDTO;
import com.example.PersonApi.DTO.PersonDTO;
import com.example.PersonApi.DTO.PhoneDTO;
import com.example.PersonApi.Entity.Health;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Collections;

import java.util.Date;
import java.util.List;

@Builder
public class PersonBuilderDTO {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String firstName="Lucas";

    @Builder.Default
    private String lastName="Oliveira";

    @Builder.Default
    private String cpf="10546062695";

    @Builder.Default
    private LocalDate birthDate= LocalDate.parse("2000-02-15");

    @Builder.Default
    private List<PhoneDTO> phones=Collections.singletonList(PhoneBuilderDTO.builder().build().phoneDTO());

    @Builder.Default
    private HealthDTO healthInfo=HealthBuilderDTO.builder().build().healthDTO();

    public PersonDTO personDTO(){
        return new PersonDTO(id,
                firstName,
                lastName,
                cpf,
                birthDate,
                phones,
                healthInfo);


    }




}
