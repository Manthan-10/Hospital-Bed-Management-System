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
import com.devcenter.bedManagement.jwt.models.Bed;
import com.devcenter.bedManagement.jwt.repository.BedRepository;
import com.devcenter.bedManagement.jwt.security.services.BedService;

/**
 * Controller
 * @author MN097789
 *
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/bed")
public class BedController {
	
	private static final Logger logger = LoggerFactory.getLogger(BedController.class.getName());

	
	@Autowired
	BedRepository bedRepository;
	
	@Autowired
	BedService bedService;
	
	 
		/**
		 * Function createBed is mapped to /api/test/bed/addbed for adding beds
		 * @param Bed Represents the JSON data sent from the client via POST request
		 */
	@PostMapping("/addbed")
	public void createBed(@RequestBody Bed bed) {
		try {
			 bedService.createBed(bed);
		}
		catch(RecordAlreadyExists e) {
			return;
		}
		catch(ServerFailure e) {
			return;
		}
		
	}
	
	
	/**
	 * Function getBeds is mapped to /api/test/bed/getallbeds for getting all beds
	 * @return Returns List of all beds
	 */
	@GetMapping("/getallbeds")
	public ResponseEntity<Object> getBeds() {
		try {
			return new ResponseEntity<>(bedService.getBeds(),HttpStatus.OK);
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
	 * Function getBedsByRoom is mapped to /api/test/bed/getbedsbyrid/{id} url pattern
	 * which fetches the details of all the beds for the given Room id 
	 * @param is id, which represents Room Id
	 * @return All Beds in that particular Room and its details
	 * 
	 */
	@GetMapping("/getbedsbyrid/{id}")
	public  ResponseEntity<Object> getBedsByRoom(@PathVariable int id){
		try {
			return new ResponseEntity<>(bedService.getBedsByRoom(id),HttpStatus.OK);
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
