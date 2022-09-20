package com.devcenter.bedManagement.jwt.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.devcenter.bedManagement.jwt.models.BedAssignment;
import com.devcenter.bedManagement.jwt.repository.BedAssignmentRepository;
import com.devcenter.bedManagement.jwt.security.services.BedAssignmentService;


/**
 * Controller
 * @author MN097789
 *
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/bedassignment")
public class BedAssignmentController {
	
	private static final Logger logger = LoggerFactory.getLogger(BedAssignmentController.class.getName());
	
	@Autowired
	BedAssignmentRepository bedAssignmentRepository;
	
	@Autowired
	BedAssignmentService bedAssignmentService;
	
	
	/**
	 * Function createPatientBed is mapped to /api/test/bedassignment/assign for assigning a bed to patient
	 * @param BedAssignment Represents the JSON data sent from the client via POST request
	 */
	@PostMapping("/assign")
	public void createPatientBed(@RequestBody BedAssignment bedAssignment) {
		try {
			bedAssignmentService.createPatientBed(bedAssignment);  
		}
		catch(RecordAlreadyExists e) {
			return;
		}
		catch(ServerFailure e) {
			return;
		}  
	}
	

	/**
	 * Function getPatientBed is mapped to /api/test/bedassignment/get for getting all bed assignments
	 * @return Returns List of all bed assignments
	 */
	@GetMapping("/get")
	public ResponseEntity<Object> getPatientBed(){
		try {
			return new ResponseEntity<>(bedAssignmentService.getPatientBed(), HttpStatus.OK);
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
	 * Function getBedAssignmentByBed is mapped to /api/test/bedassignment/getbedassignmentbybed/{id} url pattern
	 * which fetches the details of bed assignment for the given bed id 
	 * @param is id, which represents Bed Id
	 * @return The Bed Assignment in that particular Bed and its details
	 * 
	 */
	@GetMapping("/getbedassignmentbybed/{id}")
	public ResponseEntity<Object> getBedAssignmentByBed(@PathVariable int id){
		try {
			return new ResponseEntity<>(bedAssignmentService.getBedAssignmentByBeds(id),HttpStatus.OK);
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
	
	
	/**
	 * Function getBedAssignmentByPatient is mapped to /api/test/bedassignment/getbedassignmentbypatient/{id} url pattern
	 * which fetches the details of bed assignment for the given patient id 
	 * @param is id, which represents Patient Id
	 * @return The Bed Assignment for that particular Patient and its details
	 * 
	 */
	@GetMapping("/getbedassignmentbypatient/{id}")
	public ResponseEntity<Object> getBedAssignmentByPatient(@PathVariable int id){
		try {
			return new ResponseEntity<>(bedAssignmentService.getBedAssignmentByPatient(id),HttpStatus.OK);
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
	
	
	/**
	 * Function deleteBedAssignmentByPatient is mapped to /api/test/bedassignment/deletebedassignmentbypatient/{id} url pattern
	 * which deletes the  bed assignment for the given patient id 
	 * @param is id, which represents Patient Id
	 * 
	 */
	@DeleteMapping("/deletebedassignmentbypatient/{id}")
	public void deleteBedAssignmentByPatient(@PathVariable int id){
		try {
			bedAssignmentService.deleteBedAssignmentByPatient(id);
		}
		catch(RecordDoesNotExist e)
		{
			logger.error("Record Does Not Exist");  
			return;
		}
		catch(ServerFailure e)
		{
			logger.error("Retrieval Failed");
			return;
		}
		
	}
	

}
