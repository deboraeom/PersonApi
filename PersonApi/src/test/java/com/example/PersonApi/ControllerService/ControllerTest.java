package com.example.PersonApi.ControllerService;

import com.example.PersonApi.Builder.PersonBuilderDTO;
import com.example.PersonApi.Controller.PersonController;
import com.example.PersonApi.DTO.MessageDTO;
import com.example.PersonApi.DTO.PersonDTO;
import com.example.PersonApi.Exception.PersonNotFound;
import com.example.PersonApi.Service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static com.example.PersonApi.JsonConvertionUtil.JavaConvertionUtil.asJsonString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {
    private static final String PERSON_API_URL_PATH = "/api/v1/person";

    private MockMvc mockMvc;


    @Mock
    private PersonService personService;


    @InjectMocks
    private PersonController personController;

    @BeforeEach
    void setUp(){
        mockMvc= MockMvcBuilders.standaloneSetup(personController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception{
        PersonDTO personDTO = PersonBuilderDTO.builder().build().personDTO();
        personDTO.setFirstName(null);

              mockMvc.perform(post(PERSON_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(personDTO)))
                .andExpect(status().isBadRequest());

    }


    @Test
    void whenPOSTIsCalledThenPersonIsCreated() throws Exception{

        PersonDTO personDTO= PersonBuilderDTO.builder().build().personDTO();
        MessageDTO messageDTO = MessageDTO.builder().message("Person Created successfully").build();
        when(personService.savePerson(personDTO)).thenReturn(messageDTO);
        mockMvc.perform(post(PERSON_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(personDTO)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.message", is(messageDTO.getMessage())));
    }

    @Test
    void whenGetListIsCalledThenReturnAListAndReturnStatusOk() throws Exception {
        PersonDTO personDTO= PersonBuilderDTO.builder().build().personDTO();
        when(personService.findAllPerson()).thenReturn(Collections.singletonList(personDTO));
        mockMvc.perform(get(PERSON_API_URL_PATH))
                .andExpect(status().isOk());
   }

    @Test
    void whenGetListIsCalledThenReturnAListEmptyAndReturnStatusOk() throws Exception {

        when(personService.findAllPerson()).thenReturn(Collections.emptyList());
        mockMvc.perform(get(PERSON_API_URL_PATH))
                .andExpect(status().isOk());


    }

    @Test
    void whenGetPersonByNameIsCalledThenReturnASodaAndReturnStatusOk() throws Exception {
        PersonDTO personDTO= PersonBuilderDTO.builder().build().personDTO();
        when(personService.findPersonById(personDTO.getId())).thenReturn(personDTO);
        mockMvc.perform(get(PERSON_API_URL_PATH+ "/" + personDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName", is(personDTO.getLastName())));

    }

    @Test
    void whenGetPersonByNameIsCalledThenThrowsBecauseDoesntExist() throws Exception {
        PersonDTO sodaDTO= PersonBuilderDTO.builder().build().personDTO();
        when(personService.findPersonById(sodaDTO.getId())).thenThrow(PersonNotFound.class);
        mockMvc.perform(get(PERSON_API_URL_PATH+ "/" + sodaDTO.getId()))
                .andExpect(status().isBadRequest());
    }


    @Test
    void whenDeleteRequestThenPersonIsDeletedAndReturnStatusNotContent()throws Exception{
        Long id =1L;
        MessageDTO messageDTO=MessageDTO.builder().message("Person Deleted successfully")
                .build();
        when(personService.deletePerson(id)).thenReturn(messageDTO);
        mockMvc.perform(delete(PERSON_API_URL_PATH+ "/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is(messageDTO.getMessage())))
                .andExpect(status().isOk());

    }

    @Test
    void whenDeleteRequestButPersonDoesntExistThenReturnStatusBadRequs()throws Exception{
        Long id =1L;

        when(personService.deletePerson(id)).thenThrow(PersonNotFound.class);
        mockMvc.perform(delete(PERSON_API_URL_PATH+ "/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    void whenPutIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception{
        PersonDTO personDTO = PersonBuilderDTO.builder().build().personDTO();
        personDTO.setId(null);
        when(personService.updatePerson(personDTO)).thenThrow(PersonNotFound.class);
        mockMvc.perform(put(PERSON_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(personDTO)))
                .andExpect(status().isBadRequest());

    }


    @Test
    void whenPUtIsCalledThenPersonIsCreated() throws Exception{

        PersonDTO personDTO = PersonBuilderDTO.builder().build().personDTO();
        personDTO.setLastName(null);
        mockMvc.perform(put(PERSON_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(personDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenPUTIsCalledThenPersonIsCreated() throws Exception{

        PersonDTO personDTO= PersonBuilderDTO.builder().build().personDTO();
        MessageDTO messageDTO = MessageDTO.builder().message("Person Update successfully").build();
       when(personService.updatePerson(personDTO)).thenReturn(messageDTO);
        mockMvc.perform(put(PERSON_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(personDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(messageDTO.getMessage())))
                .andDo(print());
    }

}
