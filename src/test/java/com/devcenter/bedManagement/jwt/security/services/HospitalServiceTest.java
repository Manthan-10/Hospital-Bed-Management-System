package com.devcenter.bedManagement.jwt.security.services;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.devcenter.bedManagement.jwt.models.Hospital;
import com.devcenter.bedManagement.jwt.repository.HospitalRepository;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HospitalServiceTest {
	
	@Mock
	HospitalRepository hospitalRepository;
	
	@InjectMocks
	HospitalService hospitalService; 
	
	@Mock
	private ModelMapper modelMapper;
	
	@Test
    void SaveHospitalSuccessTest(){
		Hospital hosp = new Hospital(1, "test", "test","7778");
        Mockito.when(hospitalRepository.save(Mockito.any(Hospital.class))).thenReturn(hosp); 
        Assertions.assertEquals(hosp, hospitalService.createHospital(hosp));
    }
	
	@Test
	void FetchAllHospitalsSuccessTest() {
		
		Hospital hosp1 = new Hospital(1,"test1", "test1", "01737186095");
		
		List<Hospital> hospitalList = new ArrayList<>(Arrays.asList(hosp1, hosp1, hosp1 ));
		
		
		Mockito.when(hospitalRepository.findAll()).thenReturn(hospitalList);
		Assertions.assertEquals(hospitalList, hospitalService.getHospitals()); 
		
	}
	
	
	@Test
	void FetchHospitalByIdSuccessTest() {
		Optional<Hospital> hosp = Optional.of(new Hospital(1, "test", "test","7778"));
		Hospital hosp1 = new Hospital(1,"test", "test", "7778");
		
		Mockito.when(hospitalRepository.findById(1)).thenReturn(hosp);
		Mockito.when(modelMapper.map(hosp, Hospital.class)).thenReturn(hosp1);
		Assertions.assertEquals(hosp1, hospitalService.getHospitalById(1)); 
		
	}

}
