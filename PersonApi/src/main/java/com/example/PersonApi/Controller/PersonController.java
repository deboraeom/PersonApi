package com.example.PersonApi.Controller;

import com.example.PersonApi.DTO.MessageDTO;
import com.example.PersonApi.DTO.PersonDTO;
import com.example.PersonApi.Exception.PersonNotFound;
import com.example.PersonApi.Service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
@AllArgsConstructor
public class PersonController {
    private PersonService personService;

    @PostMapping
    public MessageDTO save(@RequestBody @Valid PersonDTO personDTO){
        System.out.println(personDTO.toString());
        return (personService.savePerson(personDTO));
    }

    @GetMapping
    public List<PersonDTO> findAllPerson(){

        return personService.findAllPerson();
    }

    @GetMapping("/{id}")
    public PersonDTO findOnePersonByID(@PathVariable Long id) throws PersonNotFound {
        return personService.findPersonById(id);
    }

    @PutMapping
    public MessageDTO updatePerson(@RequestBody @Valid PersonDTO personDTO) throws PersonNotFound {
        return  personService.updatePerson(personDTO);

    }

    @DeleteMapping("/{id}")
    public MessageDTO deletePerson(@PathVariable long id) throws PersonNotFound {
        return personService.deletePerson(id);
    }

}
