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

import com.devcenter.bedManagement.jwt.models.Bed;
import com.devcenter.bedManagement.jwt.models.Floor;
import com.devcenter.bedManagement.jwt.models.Hospital;
import com.devcenter.bedManagement.jwt.models.Room;
import com.devcenter.bedManagement.jwt.repository.BedRepository;
import com.devcenter.bedManagement.jwt.security.services.BedService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BedControllerTest {
	
	@MockBean
	BedRepository bedRepository;
	
	@MockBean
	BedService bedService;
	
	@Autowired
	BedController bedController;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@BeforeEach
	public void setUp() {      
	    mockMvc = MockMvcBuilders.standaloneSetup(bedController).build(); 
	}
	
	@Test
	public void saveBedSuccessTest() throws Exception{
		
		Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		Floor floor = new Floor(3, "Fifth", hosp1);
		Room room = new Room(4, "677", floor);
		
		Bed bed = new Bed(2, "101-c", room);
		
		Mockito.doNothing().when(bedService).createBed(Mockito.any(Bed.class));
		
		RequestBuilder request = MockMvcRequestBuilders.post("/api/test/bed/addbed")
                .content(asJsonString(bed))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

			MvcResult mvcResult = mockMvc.perform(request).andReturn();
			Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	
	
	@Test
	public void fetchAllBedsSuccessTest() throws Exception{
		
		 Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		 Floor floor = new Floor(3, "Fifth", hosp1);
		 Room room = new Room(4, "677", floor);
		 
		 List<Bed> bedList = Arrays.asList(
	                new Bed(1,"105-a", room),
	                new Bed(2, "567-c", room),
	                new Bed(3, "789-e", room)
	        );
		 
		 when(bedService.getBeds()).thenReturn(bedList);

	        mockMvc.perform(
	                MockMvcRequestBuilders.get("/api/test/bed/getallbeds"))
	                .andExpect(status().isOk())
	                .andExpect(content().json("[{}, {}, {}]"));
	}
	
	@Test
	public void fetchAllBedsByBedIdTest()  throws Exception{
		
		Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		 Floor floor = new Floor(3, "Fifth", hosp1);
		 Room room = new Room(4, "677", floor);
		 
		 List<Bed> bedList = Arrays.asList(
	                new Bed(1,"105-a", room),
	                new Bed(2, "567-c", room),
	                new Bed(3, "789-e", room)
	        );
		
		when(bedService.getBedsByRoom(4)).thenReturn(bedList);
		
		mockMvc.perform(
                MockMvcRequestBuilders.get("/api/test/bed/getbedsbyrid/4"))
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
