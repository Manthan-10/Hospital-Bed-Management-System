package com.devcenter.bedManagement.jwt.security.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcenter.bedManagement.jwt.models.Hospital;
import com.devcenter.bedManagement.jwt.repository.HospitalRepository;

/**
 * Service class
 * @author MN097789
 *
 */
@Service
public class HospitalService { 
	
	@Autowired
	HospitalRepository hospitalRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	

	/**
	 * Function adds new hospitals
	 * @param Hospital Represents the hospital object
	 */
		public Hospital createHospital(Hospital hospital) {
		     return hospitalRepository.save(hospital);  
		}
		
		/**
		 * Function fetches all hospitals
		 * @return Returns List of all hospitals
		 */
		public List<Hospital> getHospitals() {
			return hospitalRepository.findAll(); 
			
		}
		
		/**
		 * Function fetches the details of the hospital for the given id 
		 * @param is id, which represents Hospital Id
		 * @return Hospital and its details
		 * 
		 */
		public Hospital getHospitalById(int id) {
			return this.modelMapper.map(hospitalRepository.findById(id), Hospital.class);
		}

}
