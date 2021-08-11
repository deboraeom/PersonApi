package com.example.PersonApi.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@Builder
public class MessageDTO {
    private String message;
}
