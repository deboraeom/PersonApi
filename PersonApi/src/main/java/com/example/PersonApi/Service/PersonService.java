package com.example.PersonApi.Service;


import com.example.PersonApi.DTO.MessageDTO;
import com.example.PersonApi.DTO.PersonDTO;
import com.example.PersonApi.Exception.PersonNotFound;
import com.example.PersonApi.Mapper.PersonMapper;
import com.example.PersonApi.Repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;


@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;

    }

    public PersonDTO verifyById(Long id) throws PersonNotFound {
        return personMapper.toDTO(personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFound(id)));
    }

    public MessageDTO savePerson(PersonDTO personDTO) {
        personRepository.save(personMapper.toModel(personDTO));
        return MessageDTO.builder()
                .message("Person Created successfully")
                .build();
    }

    public List<PersonDTO> findAllPerson(){
        return personRepository.findAll().stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findPersonById(Long id) throws PersonNotFound {
        return verifyById(id);
    }

    public MessageDTO deletePerson(Long id) throws PersonNotFound {
        personRepository.delete(personMapper.toModel(verifyById(id)));
        return MessageDTO.builder()
                .message("Person Deleted successfully").build();
    }

    public MessageDTO updatePerson(PersonDTO personDTO) throws PersonNotFound {
        if(isEmpty(personDTO.getId())) throw  new PersonNotFound(0L );
        verifyById(personDTO.getId());
        personRepository.save(personMapper.toModel(personDTO));
        return MessageDTO.builder()
                .message("Person update with successfully")
                .build();

    }


}
