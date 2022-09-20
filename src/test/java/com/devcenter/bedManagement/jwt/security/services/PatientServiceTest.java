package com.devcenter.bedManagement.jwt.security.services;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.devcenter.bedManagement.jwt.models.Hospital;
import com.devcenter.bedManagement.jwt.models.Patient;
import com.devcenter.bedManagement.jwt.repository.PatientRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientServiceTest {
	
	@MockBean
	PatientRepository patientRepository;
	
	@MockBean
	PatientService patientService;
	
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
		 
		Mockito.when(patientRepository.findAll()).thenReturn(patientList);
		Assertions.assertEquals(patientList, patientRepository.findAll());  
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
		 
		Mockito.when(patientRepository.getPatientsByHospital(hosp)).thenReturn(patientList);
		Assertions.assertEquals(patientList, patientRepository.getPatientsByHospital(hosp)); 
		Assertions.assertEquals(3, patientRepository.getPatientsByHospital(hosp).size());
	}
	
	

}
