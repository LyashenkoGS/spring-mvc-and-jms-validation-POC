package com.example.demo;

import com.example.demo.dto.AnotherDTO;
import com.example.demo.dto.SomeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MyControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @SpyBean
    private MyService service;

    private SomeDTO someDTO;
    private AnotherDTO anotherDTO;

    @Before
    public void setUp() {
        someDTO = TestDtoFactory.getValidSomeDto();
        anotherDTO = TestDtoFactory.getValidAnotherDto();
    }

    @Test
    public void validSomeDtoRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/somedto")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(someDTO)));
        //so a service method should be invoked
        Mockito.verify(service, VerificationModeFactory.only()).processSomeDTO(someDTO);
    }

    @Ignore//FIXME need to add custom validation (no javax) to controllers
    @Test
    public void invalidSomeDtoRequest() throws Exception {
        //given a corrupted dto
        BigDecimal invalidValue = someDTO.getNumberOfSomething().add(BigDecimal.ONE);
        someDTO.setNumberOfSomething(invalidValue);
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/somedto")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(someDTO)));
        //so a service method SHOULDN'T be invoked
        Mockito.verify(service, VerificationModeFactory.noMoreInteractions()).processSomeDTO(someDTO);
    }


    @Test
    public void validAnotherDtoRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/anotherdto")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(anotherDTO)));
        //so a service method should be invoked
        Mockito.verify(service, VerificationModeFactory.only()).processAnotherDTO(anotherDTO);
    }

    @Ignore//FIXME need to add custom validation (no javax) to controllers
    @Test
    public void invalidAnotherDtoRequest() throws Exception {
        //given a corrupted dto
        BigDecimal invalidValue = anotherDTO.getSomeMagicNumber().add(BigDecimal.ONE);
        anotherDTO.setSomeMagicNumber(invalidValue);
        mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/anotherdto")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(anotherDTO)));
        //so a service method SHOULDN'T be invoked
        Mockito.verify(service, VerificationModeFactory.noMoreInteractions()).processAnotherDTO(anotherDTO);
    }

}
