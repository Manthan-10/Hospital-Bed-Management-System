package com.devcenter.bedManagement.jwt.security.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcenter.bedManagement.jwt.models.Bed;
import com.devcenter.bedManagement.jwt.models.Room;
import com.devcenter.bedManagement.jwt.repository.BedRepository;
import com.devcenter.bedManagement.jwt.repository.RoomRepository;


/**
 * Service class
 * @author MN097789
 *
 */

@Service
public class BedService {
	
	@Autowired
	BedRepository bedRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * Function for adding new beds
	 * @param bed represents Bed object
	 * 	 
	 **/
	public void createBed(Bed bed) {
		 bedRepository.save(bed);
	}
	
	

	/**
	 * Function fetches all beds
	 * @return Returns List of all beds
	 */
	public List<Bed> getBeds() {
		return bedRepository.findAll(); 
	}
	
	
	/**
	 * Function fetches the details of all the beds for the given Room id 
	 * @param is id, which represents Room Id
	 * @return All Beds in that particular Room and its details
	 * 
	 */
	public List<Bed> getBedsByRoom(int id){
		
		Optional<Room> room = roomRepository.findById(id);
		List<Bed> beds = bedRepository.getBedsByRoom(this.modelMapper.map(room, Room.class));
		
		return beds;
		
	}
}
