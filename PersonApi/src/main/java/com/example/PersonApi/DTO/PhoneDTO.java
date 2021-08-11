package com.example.PersonApi.DTO;

import com.example.PersonApi.Enum.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

    private  Long id;
    private PhoneType type;
    private String number;


}
