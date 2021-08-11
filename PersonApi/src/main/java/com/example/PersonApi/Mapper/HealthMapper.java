package com.example.PersonApi.Mapper;

import com.example.PersonApi.DTO.HealthDTO;
import com.example.PersonApi.Entity.Health;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HealthMapper {
    HealthMapper INSTANCE = Mappers.getMapper(HealthMapper.class);

    Health toModel(HealthDTO healthDTO);

    HealthDTO toDTO(Health health);

}
