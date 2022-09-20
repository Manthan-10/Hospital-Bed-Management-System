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
import com.devcenter.bedManagement.jwt.models.Floor;
import com.devcenter.bedManagement.jwt.repository.FloorRepository;
import com.devcenter.bedManagement.jwt.security.services.FloorService;

/**
 * Controller
 * @author MN097789
 *
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/floor")
public class FloorController {
	
	private static final Logger logger = LoggerFactory.getLogger(FloorController.class.getName());
	
	@Autowired
	FloorRepository floorRepository;
	
	@Autowired
	FloorService floorService;
	

	/**
	 * Function createFloor is mapped to /api/test/floor/addfloor for adding floors
	 * @param Floor Represents the JSON data sent from the client via POST request
	 */
	@PostMapping("/addfloor")
	public void createFloor(@RequestBody Floor floor) { 
		try {
			floorService.createFloor(floor);
		}
		catch(RecordAlreadyExists e) {
			return;
		}
		catch(ServerFailure e) {
			return;
		}
		  
	}
	
	/**
	 * Function getFloors is mapped to /api/test/floor/getallfloors for getting all floors
	 * @return Returns List of all floors
	 */
	@GetMapping("/getallfloors")
	public ResponseEntity<Object> getFloors() {
		try {
			return new ResponseEntity<>(floorService.getFloors(), HttpStatus.OK);
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
	 * Function getFloorsByHospital is mapped to /api/test/floor/getbyhid/{id} url pattern
	 * which fetches the details of all the floors for the given Hospital id 
	 * @param is id, which represents Hospital Id
	 * @return All floors in that particular hospital and its details
	 * 
	 */
	@GetMapping("/getbyhid/{id}")
	public ResponseEntity<Object> getFloorsByHospital(@PathVariable int id) {
		
		try {
			return new ResponseEntity<>(floorService.getFloorsByHospital(id),HttpStatus.OK); 
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
