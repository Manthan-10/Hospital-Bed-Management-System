package com.devcenter.bedManagement.jwt.security.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.devcenter.bedManagement.jwt.models.Bed;
import com.devcenter.bedManagement.jwt.models.BedAssignment;
import com.devcenter.bedManagement.jwt.models.Floor;
import com.devcenter.bedManagement.jwt.models.Hospital;
import com.devcenter.bedManagement.jwt.models.Patient;
import com.devcenter.bedManagement.jwt.models.Room;
import com.devcenter.bedManagement.jwt.repository.BedAssignmentRepository;
import com.devcenter.bedManagement.jwt.repository.BedRepository;
import com.devcenter.bedManagement.jwt.repository.PatientRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BedAssignmentServiceTest {
	
	@MockBean
	BedAssignmentRepository bedAssignmentRepository;
	
	@MockBean
	PatientRepository patientRepository;
	
	@MockBean
	BedRepository bedRepository;
	
	@MockBean
	BedAssignmentService bedAsignmentService; 
	
	@Test
    void SavePatientBedAssignmentSuccessTest(){
		
		Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		Floor floor = new Floor(3, "Fifth", hosp1);
		Room room = new Room(4, "677", floor);
		
		Bed bed = new Bed(2, "101-c", room);
		
		String sDate1="1998-04-10";  
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
		} catch (ParseException e) {
			e.printStackTrace();  
		}
		
		Patient patient = new Patient(1, "name", "12", "Male", date1, "address", "896676767688", "76768866", hosp1);
		
		BedAssignment bedAssignment= new BedAssignment(1, bed, patient);
		
        Mockito.when(bedAssignmentRepository.save(Mockito.any(BedAssignment.class))).thenReturn(bedAssignment);
        Assertions.assertEquals(bedAssignment, bedAssignmentRepository.save(bedAssignment)); 
    }
	
	@Test
	void FetchAllPatientBedSuccessTest() throws ParseException {
		
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
		
		Mockito.when(bedAssignmentRepository.findAll()).thenReturn(bedAssignmentList);
		Assertions.assertEquals(bedAssignmentList, bedAssignmentRepository.findAll());
		
	}
	
	@Test 
	public void FetchAllBedsByRIdTest() throws ParseException {
		
		Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		Floor floor1 = new Floor(3, "Fifth", hosp1);
		Room room1 = new Room(4, "677", floor1);
		
		Bed bed1 = new Bed(2, "101-c", room1);
		
		String sDate1="1998-04-10";  
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
		Patient patient1 = new Patient(1, "name", "12", "Male", date1, "address", "896676767688", "76768866", hosp1);
		
			
		BedAssignment bedAssignmentList = new BedAssignment(1,bed1, patient1);
		
		Mockito.when(bedAssignmentRepository.getBedAssignmentByBeds(bed1)).thenReturn(bedAssignmentList);
		Assertions.assertEquals(bedAssignmentList, bedAssignmentRepository.getBedAssignmentByBeds(bed1));	
		
	}
	
	
	@Test 
	public void FetchAllBedsByPidTest() throws ParseException {
		
		Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		Floor floor1 = new Floor(3, "Fifth", hosp1);
		Room room1 = new Room(4, "677", floor1);
		
		Bed bed1 = new Bed(2, "101-c", room1);
		
		String sDate1="1998-04-10";  
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
		Patient patient1 = new Patient(1, "name", "12", "Male", date1, "address", "896676767688", "76768866", hosp1);
		
			
		BedAssignment bedAssignmentList = new BedAssignment(1,bed1, patient1);
		
		Mockito.when(bedAssignmentRepository.getBedAssignmentByPatient(patient1)).thenReturn(bedAssignmentList);
		Assertions.assertEquals(bedAssignmentList, bedAssignmentRepository.getBedAssignmentByPatient(patient1));
		
	}

}
