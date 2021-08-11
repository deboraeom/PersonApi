package com.example.PersonApi.JsonConvertionUtil;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JavaConvertionUtil {


        public static String asJsonString(Object objectDto){
            try{
                ObjectMapper objectMapper= new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
                objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
                objectMapper.registerModules(new JavaTimeModule());

                return objectMapper.writeValueAsString(objectDto);

            }catch (Exception e){
                throw  new RuntimeException(e);
            }
        }
    }


