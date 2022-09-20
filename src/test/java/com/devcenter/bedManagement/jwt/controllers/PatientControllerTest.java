package com.devcenter.bedManagement.jwt.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.devcenter.bedManagement.jwt.models.Hospital;
import com.devcenter.bedManagement.jwt.models.Patient;
import com.devcenter.bedManagement.jwt.repository.PatientRepository;
import com.devcenter.bedManagement.jwt.security.services.PatientService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PatientControllerTest {
	
	@MockBean
	PatientRepository patientRepository;
	
	@MockBean
	PatientService patientService;
	
	@Autowired
	PatientController patientController;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@BeforeEach
	public void setUp() {      
	    mockMvc = MockMvcBuilders.standaloneSetup(patientController).build(); 
	}
	
	@Test
	public void fetchAllPatientsSuccessTest() throws Exception{
		
		String sDate1="1998-04-10";  
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
		
		Hospital hosp = new Hospital(1, "test", "test","7778");

		List<Patient> patientList = Arrays.asList(
				new Patient(1, "name1", "14", "Male", date1, "add", "6767688", "8866", hosp),
				new Patient(2, "name2", "30", "Female", date1, "addres", "8966", "866", hosp),
				new Patient(3, "name3", "50", "Male", date1, "adres", "66767", "6886", hosp)
                
        );
		 
		 when(patientService.getPatients()).thenReturn(patientList);

	        mockMvc.perform( 
	                MockMvcRequestBuilders.get("/api/test/patients"))
	                .andExpect(status().isOk())
	                .andExpect(content().json("[{}, {}, {}]"));
	}
	
	
	@Test
	public void fetchPatientsByHidSuccessTest() throws Exception{
		
		String sDate1="1998-04-10";  
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
		
		Hospital hosp = new Hospital(1, "test", "test","7778");

		List<Patient> patientList = Arrays.asList(
				new Patient(1, "name1", "14", "Male", date1, "add", "6767688", "8866", hosp),
				new Patient(2, "name2", "30", "Female", date1, "addres", "8966", "866", hosp),
				new Patient(3, "name3", "50", "Male", date1, "adres", "66767", "6886", hosp)
                
        );
		 
		 when(patientService.getPatientsByHospital(1)).thenReturn(patientList);

	        mockMvc.perform(
	                MockMvcRequestBuilders.get("/api/test//patients/byhospitalid/1"))
	                .andExpect(status().isOk())
	                .andExpect(content().json("[{}, {}, {}]"));
	}
	
	
	

}
