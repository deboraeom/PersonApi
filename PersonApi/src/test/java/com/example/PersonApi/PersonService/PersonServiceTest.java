package com.example.PersonApi.PersonService;

import com.example.PersonApi.Builder.PersonBuilderDTO;
import com.example.PersonApi.DTO.MessageDTO;
import com.example.PersonApi.DTO.PersonDTO;
import com.example.PersonApi.Entity.Person;
import com.example.PersonApi.Exception.PersonNotFound;
import com.example.PersonApi.Mapper.PersonMapper;
import com.example.PersonApi.Repository.PersonRepository;
import com.example.PersonApi.Service.PersonService;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

     private final Long id = 1L;
    PersonMapper personMapper = PersonMapper.INSTANCE;

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;



    @Test
    public  void  whenSendValidPersonReturnAMessageDTO(){
        PersonDTO personDTOToSave = PersonBuilderDTO.builder().build().personDTO();
        Person personTOSave = personMapper.toModel(personDTOToSave);
        MessageDTO messageDTO = MessageDTO.builder()
                .message("Person Created successfully")
                .build();
        when(personRepository.save(personTOSave)).thenReturn(personTOSave);
        assertThat(personService.savePerson(personDTOToSave), CoreMatchers.is(equalTo(messageDTO)));


    }


    @Test
    public void whenRequestAllPersonReturnList(){
        PersonDTO personDTOToSave = PersonBuilderDTO.builder().build().personDTO();
        Person personTOSave = personMapper.toModel(personDTOToSave);
        when(personRepository.findAll()).thenReturn(Collections.singletonList(personTOSave));
        assertThat(personService.findAllPerson(),CoreMatchers.is(equalTo(Collections.singletonList(personMapper.toDTO(personTOSave)))));

    }

    @Test
    public void whenRequestAllPersonReturnEmptyList(){
        PersonDTO personDTO = PersonBuilderDTO.builder().build().personDTO();
        Person personTO = personMapper.toModel(personDTO);
        when(personRepository.findAll()).thenReturn(Collections.emptyList());

        assertThat(personService.findAllPerson(),CoreMatchers.is(equalTo(Collections.emptyList())));

    }


    @Test
    public void whenRequestOnePersonReturnOnePerson() throws PersonNotFound {
        PersonDTO personDTO = PersonBuilderDTO.builder().build().personDTO();
        Person person = personMapper.toModel(personDTO);
        when(personRepository.findById(id)).thenReturn(Optional.of(person));

        assertThat(personService.findPersonById(id),CoreMatchers.is(equalTo(personMapper.toDTO(person))));

    }

    @Test
    public void whenRequestANotRegisteredPersonThanReturnThrowsNotFoundPerson() throws PersonNotFound {
        PersonDTO personDTO = PersonBuilderDTO.builder().build().personDTO();
        Person person = personMapper.toModel(personDTO);
        when(personRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(PersonNotFound.class,()->personService.findPersonById(id));

    }

    @Test
    public void whenRequestDeleteAPersonSucessfully() throws PersonNotFound {
        PersonDTO personDTO = PersonDTO.builder().build();
        Person person = personMapper.toModel(personDTO);
        Long id=1L;
        when(personRepository.findById(id)).thenReturn(Optional.of(person));
        doNothing().when(personRepository).delete(person);
        personService.deletePerson(id);
        verify(personRepository, times(1)).findById(id);
        verify(personRepository, times(1)).delete(person);
    }


    @Test
    void whenRequestDeletedPersonButNotFound() throws PersonNotFound {
        PersonDTO personDTO = PersonDTO.builder().build();
        Person person = personMapper.toModel(personDTO);
        Long id=1L;
        when(personRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(PersonNotFound.class,()->personService.deletePerson(id));
    }

    @Test
    void whenRequestUpdateThenReturnMessageDTO() throws PersonNotFound {
        PersonDTO personDTOToUpdate = PersonBuilderDTO.builder().build().personDTO();
        Person personTOUpdate = personMapper.toModel(personDTOToUpdate);
        MessageDTO messageDTO = MessageDTO.builder()
                .message("Person update with successfully")
                .build();
        when(personRepository.findById(personTOUpdate.getId())).thenReturn(Optional.of(personTOUpdate));
        when(personRepository.save(personTOUpdate)).thenReturn(personTOUpdate);
        assertThat(personService.updatePerson(personDTOToUpdate), CoreMatchers.is(equalTo(messageDTO)));

    }


    @Test
    void whenRequestUpdateWithNullIdThenReturnThrowsPersonNotFound() throws PersonNotFound {
        PersonDTO personDTOToUpdate = PersonBuilderDTO.builder().build().personDTO();
        personDTOToUpdate.setId(null);
        assertThrows(PersonNotFound.class,()->personService.updatePerson(personDTOToUpdate));

    }

    @Test
    void whenRequestUpdateANotRegisteredThenReturnThrowsPersonNotFound() throws PersonNotFound {
        PersonDTO personDTOToUpdate = PersonBuilderDTO.builder().build().personDTO();
        personDTOToUpdate.setId(null);
        assertThrows(PersonNotFound.class,()->personService.updatePerson(personDTOToUpdate));

    }
}
