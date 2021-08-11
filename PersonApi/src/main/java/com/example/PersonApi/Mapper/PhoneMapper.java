package com.example.PersonApi.Mapper;

import com.example.PersonApi.DTO.PersonDTO;
import com.example.PersonApi.DTO.PhoneDTO;

import com.example.PersonApi.Entity.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhoneMapper {
    PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);

    Phone toModel(PhoneDTO phoneDTO);

    PhoneDTO toDTO(Phone phone);
}
