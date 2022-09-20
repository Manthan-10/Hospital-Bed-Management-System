package com.devcenter.bedManagement.jwt.security.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcenter.bedManagement.jwt.models.Floor;
import com.devcenter.bedManagement.jwt.models.Room;
import com.devcenter.bedManagement.jwt.repository.FloorRepository;
import com.devcenter.bedManagement.jwt.repository.RoomRepository;


/**
 * Service class
 * @author MN097789
 *
 */
@Service
public class RoomService {
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	FloorRepository floorRepository;
	

	@Autowired
	private ModelMapper modelMapper;
	
	
	/**
	 * Function for adding new rooms
	 * @param room represents Room object
	 */
	public void createRoom(Room room) {
	     roomRepository.save(room);
	}
	
	/**
	 * Function for getting all rooms
	 * @return Returns List of all rooms
	 */
	public List<Room> getRooms() {
		return roomRepository.findAll();
	}
	
	public List<Room> getRoomsByFloor(int id) {
			
			Optional<Floor> floor = floorRepository.findById(id);
			List<Room> rooms = roomRepository.getRoomsByFloor(this.modelMapper.map(floor, Floor.class));
			
			return rooms;		

		
	}


}
