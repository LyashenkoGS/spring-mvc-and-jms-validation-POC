package com.example.demo;

import com.example.demo.dto.AnotherDTO;
import com.example.demo.dto.SomeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import java.util.LinkedList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MyControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    private SomeDTO someDTO;
    private AnotherDTO anotherDTO;

    @Before
    public void setUp() {
        someDTO = TestDtoFactory.getValidSomeDto();
        anotherDTO = TestDtoFactory.getValidAnotherDto();
    }

    @Test
    public void validSomeDtoRequest() throws Exception {
        List<Long> executionTimes = new LinkedList<>();
        int iterationsNumber = 2000;
        for (int i = 0; i < iterationsNumber; i++) {
            long startTime = System.nanoTime();
            mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/somedto")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(mapper.writeValueAsString(someDTO)));
            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;
            executionTimes.add(executionTime);
            System.out.println("Total execution time: " + executionTime + "ns");
        }
        Long totalExecutionTimes = executionTimes.stream().reduce(0L, (aLong, aLong2) -> aLong + aLong2);
        BigDecimal averageExecutionTime = BigDecimal.valueOf(totalExecutionTimes).divide(BigDecimal.valueOf(iterationsNumber), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println("Average execution time : " + averageExecutionTime + " ns");
    }

    @Test
    public void invalidSomeDtoRequest_customValidator() throws Exception {
        //given a corrupted dto
        BigDecimal invalidValue = someDTO.getNumberOfSomething().add(BigDecimal.ONE);
        someDTO.setNumberOfSomething(invalidValue);
        List<Long> executionTimes = new LinkedList<>();
        int iterationsNumber = 2000;
        for (int i = 0; i < iterationsNumber; i++) {
            long startTime = System.nanoTime();
            String response = mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/somedto")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(mapper.writeValueAsString(someDTO)))
                    .andExpect(status().isUnprocessableEntity()).andReturn().getResponse().getContentAsString();
            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;
            executionTimes.add(executionTime);
            System.out.println("Total execution time: " + executionTime + "ns");
        }
        Long totalExecutionTimes = executionTimes.stream().reduce(0L, (aLong, aLong2) -> aLong + aLong2);
        BigDecimal averageExecutionTime = BigDecimal.valueOf(totalExecutionTimes).divide(BigDecimal.valueOf(iterationsNumber), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println("Average execution time : " + averageExecutionTime + " ns");
    }

    @Test
    public void invalidSomeDtoRequest_javaxValidator() throws Exception {
        //given a corrupted dto
        someDTO.setId(-5);
        List<Long> executionTimes = new LinkedList<>();
        int iterationsNumber = 2000;
        for (int i = 0; i < iterationsNumber; i++) {
            long startTime = System.nanoTime();
            String responseBody = mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/somedto")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(mapper.writeValueAsString(someDTO)))
                    .andExpect(status().isUnprocessableEntity()).andReturn().getResponse().getContentAsString();
            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;
            executionTimes.add(executionTime);
            System.out.println("Total execution time: " + executionTime + "ns");
        }
        Long totalExecutionTimes = executionTimes.stream().reduce(0L, (aLong, aLong2) -> aLong + aLong2);
        BigDecimal averageExecutionTime = BigDecimal.valueOf(totalExecutionTimes).divide(BigDecimal.valueOf(iterationsNumber), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println("Average execution time : " + averageExecutionTime + " ns");
    }

    @Test
    public void validAnotherDtoRequest() throws Exception {
        List<Long> executionTimes = new LinkedList<>();
        int iterationsNumber = 2000;
        for (int i = 0; i < iterationsNumber; i++) {
            long startTime = System.nanoTime();
            mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/anotherdto")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(mapper.writeValueAsString(anotherDTO)));
            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;
            executionTimes.add(executionTime);
            System.out.println("Total execution time: " + executionTime + "ns");
        }
        Long totalExecutionTimes = executionTimes.stream().reduce(0L, (aLong, aLong2) -> aLong + aLong2);
        BigDecimal averageExecutionTime = BigDecimal.valueOf(totalExecutionTimes).divide(BigDecimal.valueOf(iterationsNumber), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println("Average execution time : " + averageExecutionTime + " ns");
    }

    @Test
    public void invalidAnotherDtoRequest_customValidator() throws Exception {
        //given a corrupted dto
        BigDecimal invalidValue = anotherDTO.getSomeMagicNumber().add(BigDecimal.ONE);
        anotherDTO.setSomeMagicNumber(invalidValue);
        List<Long> executionTimes = new LinkedList<>();
        int iterationsNumber = 2000;
        for (int i = 0; i < iterationsNumber; i++) {
            long startTime = System.nanoTime();
            String response = mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/anotherdto")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(mapper.writeValueAsString(anotherDTO)))
                    .andExpect(status().isUnprocessableEntity()).andReturn().getResponse().getContentAsString();
            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;
            executionTimes.add(executionTime);
            System.out.println("Total execution time: " + executionTime + "ns");
        }
        Long totalExecutionTimes = executionTimes.stream().reduce(0L, (aLong, aLong2) -> aLong + aLong2);
        BigDecimal averageExecutionTime = BigDecimal.valueOf(totalExecutionTimes).divide(BigDecimal.valueOf(iterationsNumber), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println("Average execution time : " + averageExecutionTime + " ns");
    }

    @Test
    public void invalidAnotherDtoRequest_javaxValidator() throws Exception {
        //given a corrupted dto
        anotherDTO.setId(-33);
        List<Long> executionTimes = new LinkedList<>();
        int iterationsNumber = 2000;
        for (int i = 0; i < iterationsNumber; i++) {
            long startTime = System.nanoTime();
            String response = mvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/anotherdto")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(mapper.writeValueAsString(anotherDTO)))
                    .andExpect(status().isUnprocessableEntity()).andReturn().getResponse().getContentAsString();
            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;
            executionTimes.add(executionTime);
            System.out.println("Total execution time: " + executionTime + "ns");
        }
        Long totalExecutionTimes = executionTimes.stream().reduce(0L, (aLong, aLong2) -> aLong + aLong2);
        BigDecimal averageExecutionTime = BigDecimal.valueOf(totalExecutionTimes).divide(BigDecimal.valueOf(iterationsNumber), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println("Average execution time : " + averageExecutionTime + " ns");
    }

}
