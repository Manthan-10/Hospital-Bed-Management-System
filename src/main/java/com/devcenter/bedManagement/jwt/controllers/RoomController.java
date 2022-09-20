package com.devcenter.bedManagement.jwt.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcenter.bedManagement.jwt.exception.RecordAlreadyExists;
import com.devcenter.bedManagement.jwt.exception.RecordDoesNotExist;
import com.devcenter.bedManagement.jwt.exception.ServerFailure;
import com.devcenter.bedManagement.jwt.helper.Constants;
import com.devcenter.bedManagement.jwt.models.Room;
import com.devcenter.bedManagement.jwt.repository.RoomRepository;
import com.devcenter.bedManagement.jwt.security.services.RoomService;

/**
 * Controller
 * @author MN097789
 *
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/room")
public class RoomController {
	
	private static final Logger logger = LoggerFactory.getLogger(FloorController.class.getName());

	@Autowired
	RoomRepository roomRepository;
	
	@Autowired 
	RoomService roomService;
	 
	/**
	 * Function createRoom is mapped to /api/test/room/addroom for adding rooms
	 * @param Room Represents the JSON data sent from the client via POST request
	 */
	@PostMapping("/addroom")
	public void createRoom(@RequestBody Room room) {
		try {
			roomService.createRoom(room);
		}
		catch(RecordAlreadyExists e) {
			return;
		}
		catch(ServerFailure e) {
			return;
		}
	}
	
	/**
	 * Function getRooms is mapped to /api/test/room/getallrooms for getting all rooms
	 * @return Returns List of all rooms
	 */
	@GetMapping("/getallrooms")
	public ResponseEntity<Object> getRooms() {
		
		try {
			return new ResponseEntity<>(roomService.getRooms(), HttpStatus.OK);
		}
		catch(RecordDoesNotExist e)
		{
			logger.error("Records Does Not Exist");
			return new ResponseEntity<>(Constants.RECORD_ABSENT,HttpStatus.NOT_FOUND);
		}
		catch(ServerFailure e)
		{
			logger.error("Retrieval Failed");
			return new ResponseEntity<>(Constants.LOOKUP_FAILURE,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
	}
	
	
	/**
	 * Function getRoomsByFloor is mapped to /api/test/room/getbyfid/{id} url pattern
	 * which fetches the details of all the rooms for the given Floor id 
	 * @param is id, which represents Floor Id
	 * @return All Roooms in that particular Floor and its details
	 * 
	 */
	@GetMapping("/getbyfid/{id}")
	public  ResponseEntity<Object> getRoomsByFloor(@PathVariable int id) {
		try {
			return new ResponseEntity<>(roomService.getRoomsByFloor(id),HttpStatus.OK);
		}
		catch(RecordDoesNotExist e)
		{
			logger.error("Record Does Not Exist"); 
			return new ResponseEntity<>(Constants.RECORD_ABSENT,HttpStatus.NOT_FOUND);
		}
		catch(ServerFailure e)
		{
			logger.error("Retrieval Failed");
			return new ResponseEntity<>(Constants.LOOKUP_FAILURE,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
