package com.example.PersonApi.DTO;

import com.example.PersonApi.Entity.Health;
import com.example.PersonApi.Entity.Phone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private Long id;
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String lastName;

    @NotNull
    @NotEmpty
    @CPF
    private String cpf;

    @NotNull
    private LocalDate birthDate;

    private List<PhoneDTO> phones;

    private HealthDTO healthInfo;



}
