package com.devcenter.bedManagement.jwt.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.devcenter.bedManagement.jwt.models.Hospital;
import com.devcenter.bedManagement.jwt.repository.HospitalRepository;
import com.devcenter.bedManagement.jwt.security.services.HospitalService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HopsitalControllerTest {
	
	@MockBean
	HospitalRepository hospitalRepository;
	
	@MockBean
	HospitalService hospitalService;
	
	@Autowired
	HospitalController hospitalController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {      
	    mockMvc = MockMvcBuilders.standaloneSetup(hospitalController).build();  
	}
	
	@Test 
    public void save_hospital_success() throws Exception {

        Hospital hosp = new Hospital(1, "test", "test","7778"); 

        when(hospitalService.createHospital(hosp)).thenReturn(hosp);

        RequestBuilder request = MockMvcRequestBuilders.post("/api/test/hospital/create")
                        .content(asJsonString(hosp))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);
        
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    
    }
	
	 
	@Test 
    public void fetch_all_hospital_success() throws Exception {

        List<Hospital> hospitalList = Arrays.asList(
                new Hospital(1,"test1", "test1", "01737186095"),
                new Hospital(2, "test2", "test2", "01737186096"),
                new Hospital(3, "test3", "test3", "01737186097")
        );

        when(hospitalService.getHospitals()).thenReturn(hospitalList);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/test/hospital/gethospital"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}, {}, {}]"));
    }
	
	
	@Test 
    public void fetchHospitalByIdsuccess() throws Exception {

		 Hospital hosp = new Hospital(1, "test", "test","7778"); 

        when(hospitalService.getHospitalById(1)).thenReturn(hosp);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/test/hospital/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }
	
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
