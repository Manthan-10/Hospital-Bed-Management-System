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

import com.devcenter.bedManagement.jwt.models.Floor;
import com.devcenter.bedManagement.jwt.models.Hospital;
import com.devcenter.bedManagement.jwt.repository.FloorRepository;
import com.devcenter.bedManagement.jwt.security.services.FloorService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FloorControllerTest {
	
	@MockBean
	FloorRepository floorRepository;
	
	@MockBean
	FloorService floorService;
	
	@Autowired
	FloorController floorController;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@BeforeEach
	public void setUp() {      
	    mockMvc = MockMvcBuilders.standaloneSetup(floorController).build(); 
	}
	
	@Test
	public void saveFloorSuccessTest() throws Exception{
		
		Hospital hosp = new Hospital(1, "test", "test","7778");
		Floor floor = new Floor(1, "Tenth", hosp);
		
		Mockito.doNothing().when(floorService).createFloor(Mockito.any(Floor.class));
		
		RequestBuilder request = MockMvcRequestBuilders.post("/api/test/floor/addfloor")
                .content(asJsonString(floor))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

			MvcResult mvcResult = mockMvc.perform(request).andReturn();
			Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	
	
	@Test
	public void fetchAllFloorsSuccessTest() throws Exception{
		
		 Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		 Hospital hosp2 = new Hospital(2, "test2", "test2","798");
		 Hospital hosp3 = new Hospital(3, "test3", "test3","758");
		 
		 List<Floor> floorList = Arrays.asList(
	                new Floor(1,"Second", hosp1), 
	                new Floor(2, "Tenth", hosp2),
	                new Floor(3, "Fifth", hosp3)
	        );
		 
		 when(floorService.getFloors()).thenReturn(floorList);

	        mockMvc.perform(
	                MockMvcRequestBuilders.get("/api/test/floor/getallfloors"))
	                .andExpect(status().isOk())
	                .andExpect(content().json("[{}, {}, {}]"));
	}
	
	@Test
	public void fetchAllFloorsByHospitalIdTest()  throws Exception{
		
		Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		
		List<Floor> floorList = Arrays.asList(
                new Floor(1,"Second", hosp1),
                new Floor(2, "Tenth", hosp1),
                new Floor(3, "Fifth", hosp1)
        );
		
		when(floorService.getFloorsByHospital(1)).thenReturn(floorList);
		
		mockMvc.perform(
                MockMvcRequestBuilders.get("/api/test/floor/getbyhid/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}, {}, {}]"));
		
		
		
	}
	
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
