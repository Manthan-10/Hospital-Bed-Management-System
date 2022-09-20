package com.devcenter.bedManagement.jwt.security.services;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.devcenter.bedManagement.jwt.models.Bed;
import com.devcenter.bedManagement.jwt.models.Floor;
import com.devcenter.bedManagement.jwt.models.Hospital;
import com.devcenter.bedManagement.jwt.models.Room;
import com.devcenter.bedManagement.jwt.repository.BedRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BedServiceTest {
	
	@MockBean
	BedRepository bedRepository;
	
	@MockBean
	BedService bedService; 
	
	@Test
    void SaveBedSuccessTest(){
		
		Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		Floor floor = new Floor(3, "Fifth", hosp1);
		Room room = new Room(4, "677", floor);
		
		Bed bed = new Bed(2, "101-c", room);
		
        Mockito.when(bedRepository.save(Mockito.any(Bed.class))).thenReturn(bed);
        Assertions.assertEquals(bed, bedRepository.save(bed)); 
    }
	
	@Test
	void FetchAllBedsSuccessTest() {
		
		Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		 Floor floor = new Floor(3, "Fifth", hosp1);
		 Room room = new Room(4, "677", floor);
		 
		 List<Bed> bedList = Arrays.asList(
	                new Bed(1,"105-a", room),
	                new Bed(2, "567-c", room),
	                new Bed(3, "789-e", room)
	        );
		
		Mockito.when(bedRepository.findAll()).thenReturn(bedList);
		Assertions.assertEquals(bedList, bedRepository.findAll());
		
	}
	
	@Test 
	public void FetchAllBedsByRIdTest() {
		
		Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		 Floor floor = new Floor(3, "Fifth", hosp1);
		 Room room = new Room(4, "677", floor);
		 
		 List<Bed> bedList = Arrays.asList(
	                new Bed(1,"105-a", room),
	                new Bed(2, "567-c", room),
	                new Bed(3, "789-e", room)
	        );
		
		Mockito.when(bedRepository.getBedsByRoom(room)).thenReturn(bedList);
		Assertions.assertEquals(bedList, bedRepository.getBedsByRoom(room));
		
		
		
	}

}
