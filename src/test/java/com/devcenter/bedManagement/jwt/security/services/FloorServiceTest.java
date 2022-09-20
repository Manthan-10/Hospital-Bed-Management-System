package com.devcenter.bedManagement.jwt.security.services;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.devcenter.bedManagement.jwt.models.Floor;
import com.devcenter.bedManagement.jwt.models.Hospital;
import com.devcenter.bedManagement.jwt.repository.FloorRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FloorServiceTest {
	
	@MockBean
	FloorRepository floorRepository;
	
	@MockBean
	FloorService floorService; 
	
	@Test
    void SaveFloorSuccessTest(){
		
		Hospital hosp = new Hospital(1, "test", "test","7778");
		
		Floor floor = new Floor(3, "Fifth", hosp);
		
        Mockito.when(floorRepository.save(Mockito.any(Floor.class))).thenReturn(floor);
        Assertions.assertEquals(floor, floorRepository.save(floor)); 
    }
	
	@Test
	void FetchAllFloorsSuccessTest() {
		
		Hospital hosp = new Hospital(1, "test", "test","7778");
		
		List<Floor> floorList = Arrays.asList(
                new Floor(1,"test1", hosp),
                new Floor(2, "test2", hosp),
                new Floor(3, "test3", hosp) 
        );
		
		Mockito.when(floorRepository.findAll()).thenReturn(floorList);
		Assertions.assertEquals(floorList, floorRepository.findAll());
		
	}
	
	@Test 
	public void FetchAllFloorsByHIdTest() {
		
		Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
				
		List<Floor> floorList = Arrays.asList(
                new Floor(1,"Second", hosp1),
                new Floor(2, "Tenth", hosp1),
                new Floor(3, "Fifth", hosp1)
        );
		
		Mockito.when(floorRepository.getFloorsByHospital(hosp1)).thenReturn(floorList);
		Assertions.assertEquals(floorList, floorRepository.getFloorsByHospital(hosp1));
		
		
		
	}

}
