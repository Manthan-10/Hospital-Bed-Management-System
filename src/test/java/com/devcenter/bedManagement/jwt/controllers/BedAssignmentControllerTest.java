package com.devcenter.bedManagement.jwt.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
import com.devcenter.bedManagement.jwt.models.BedAssignment;
import com.devcenter.bedManagement.jwt.models.Floor;
import com.devcenter.bedManagement.jwt.models.Hospital;
import com.devcenter.bedManagement.jwt.models.Patient;
import com.devcenter.bedManagement.jwt.models.Room;
import com.devcenter.bedManagement.jwt.repository.BedAssignmentRepository;
import com.devcenter.bedManagement.jwt.security.services.BedAssignmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BedAssignmentControllerTest {
	
	@MockBean
	BedAssignmentRepository bedAssignmentRepository;
	
	@MockBean
	BedAssignmentService bedAssignmentService;
	
	@Autowired
	BedAssignmentController bedAssignmentController;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@BeforeEach
	public void setUp() {      
	    mockMvc = MockMvcBuilders.standaloneSetup(bedAssignmentController).build(); 
	}
	
	@Test
	public void savePatientBedAssignmentSuccessTest() throws Exception{
		
		Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		Floor floor = new Floor(3, "Fifth", hosp1);
		Room room = new Room(4, "677", floor);
		
		Bed bed = new Bed(2, "101-c", room);
		
		String sDate1="1998-04-10";  
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
		Patient patient = new Patient(1, "name", "12", "Male", date1, "address", "896676767688", "76768866", hosp1);
		
		BedAssignment bedAssignment= new BedAssignment(1, bed, patient);
		
		Mockito.doNothing().when(bedAssignmentService).createPatientBed(Mockito.any(BedAssignment.class)); 
		
		RequestBuilder request = MockMvcRequestBuilders.post("/api/test/bedassignment/assign")
                .content(asJsonString(bedAssignment))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

			MvcResult mvcResult = mockMvc.perform(request).andReturn();
			Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	
	
	@Test
	public void fetchAllPatientBedAssignmentSuccessTest() throws Exception{
		
		Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		Floor floor1 = new Floor(3, "Fifth", hosp1);
		Room room1 = new Room(4, "677", floor1);
		
		Bed bed1 = new Bed(2, "101-c", room1);
		
		String sDate1="1998-04-10";  
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
		Patient patient1 = new Patient(1, "name", "12", "Male", date1, "address", "896676767688", "76768866", hosp1);
		
		
		
		Hospital hosp2 = new Hospital(1, "test2", "test2","778");
		Floor floor2 = new Floor(4, "sixth", hosp2);
		Room room2 = new Room(5, "7", floor2);
		
		Bed bed2 = new Bed(4, "301-c", room2);
		
		String sDate2="1999-12-10";  
		Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(sDate2);
		Patient patient2 = new Patient(1, "name2", "15", "Female", date2, "addres", "2232767688", "232328866", hosp1);
		
		List<BedAssignment> bedAssignmentList = Arrays.asList(
                new BedAssignment(1,bed1, patient1),
                new BedAssignment(2, bed2, patient2)
        );
		 
		 when(bedAssignmentService.getPatientBed()).thenReturn(bedAssignmentList);

	        mockMvc.perform(
	                MockMvcRequestBuilders.get("/api/test/bedassignment/get"))
	                .andExpect(status().isOk())
	                .andExpect(content().json("[{}, {}]"));
	}
	
	@Test
	public void fetchAllPatientBedAssignmentByBedIdTest()  throws Exception{
		
		Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		Floor floor1 = new Floor(3, "Fifth", hosp1);
		Room room1 = new Room(4, "677", floor1);
		
		Bed bed1 = new Bed(2, "101-c", room1);
		
		String sDate1="1998-04-10";  
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
		Patient patient1 = new Patient(1, "name", "12", "Male", date1, "address", "896676767688", "76768866", hosp1);
		
			
		BedAssignment bedAssignmentList = new BedAssignment(1,bed1, patient1);
		
		when(bedAssignmentService.getBedAssignmentByBeds(1)).thenReturn(bedAssignmentList);
		
		mockMvc.perform(
                MockMvcRequestBuilders.get("/api/test/bedassignment/getbedassignmentbybed/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}, {}")); 
		
		
		
	}
	
	
	@Test
	public void fetchAllPatientBedAssignmentByPatientIdTest()  throws Exception{
		
		Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		Floor floor1 = new Floor(3, "Fifth", hosp1);
		Room room1 = new Room(4, "677", floor1);
		
		Bed bed1 = new Bed(2, "101-c", room1);
		
		String sDate1="1998-04-10";  
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
		Patient patient1 = new Patient(1, "name", "12", "Male", date1, "address", "896676767688", "76768866", hosp1);
		
			
		BedAssignment bedAssignmentList = new BedAssignment(1,bed1, patient1);
		
		when(bedAssignmentService.getBedAssignmentByPatient(1)).thenReturn(bedAssignmentList);
		
		mockMvc.perform(
                MockMvcRequestBuilders.get("/api/test/bedassignment/getbedassignmentbypatient/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}, {}"));
	}
	
	
	
	@Test
	public void deletePatientBedAssignmentByPatientIdTest()  throws Exception{
		
		Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		Floor floor1 = new Floor(3, "Fifth", hosp1);
		Room room1 = new Room(4, "677", floor1);
		
		Bed bed1 = new Bed(2, "101-c", room1);
		
		String sDate1="1998-04-10";  
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
		Patient patient1 = new Patient(1, "name", "12", "Male", date1, "address", "896676767688", "76768866", hosp1);
		
			
		BedAssignment bedAssignmentList = new BedAssignment(1,bed1, patient1);
		
		Mockito.doNothing().when(bedAssignmentService).deleteBedAssignmentByPatient(1); 
		
		mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/test/bedassignment/deletebedassignmentbypatient/1"))
                .andExpect(status().isOk());
	}
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
