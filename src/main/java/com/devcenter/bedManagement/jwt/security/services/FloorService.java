package com.devcenter.bedManagement.jwt.security.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcenter.bedManagement.jwt.models.Floor;
import com.devcenter.bedManagement.jwt.models.Hospital;
import com.devcenter.bedManagement.jwt.repository.FloorRepository;
import com.devcenter.bedManagement.jwt.repository.HospitalRepository;


/**
 * Service Class
 * @author MN097789
 *
 */

@Service
public class FloorService {
	

	@Autowired
	FloorRepository floorRepository;
	
	@Autowired
	HospitalRepository hospitalRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	/**
	 * Function for adding new floors
	 * @param floor Represents the Floor object
	 */
	public void createFloor(Floor floor) {
	     floorRepository.save(floor); 
	} 
	
	/**
	 * Function for fetching all floors
	 * @return Returns List of all floors
	 */
	public List<Floor> getFloors() {
		return floorRepository.findAll();
	}
	
	/**
	 * Function which fetches the details of all the floors for the given Hospital id 
	 * @param is id, which represents Hospital Id
	 * @return All floors in that particular hospital and its details
	 * 
	 */
	public List<Floor> getFloorsByHospital(Integer id) {
		
		Optional<Hospital> hosp = hospitalRepository.findById(id); 
		
		List<Floor> floors = floorRepository.getFloorsByHospital(this.modelMapper.map(hosp, Hospital.class));
		
		return floors;
		
		
	}

}
