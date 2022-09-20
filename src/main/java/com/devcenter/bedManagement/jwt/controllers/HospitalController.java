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
import com.devcenter.bedManagement.jwt.models.Hospital;
import com.devcenter.bedManagement.jwt.security.services.HospitalService;

/**
 * Controller
 * @author MN097789
 *
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/hospital")
public class HospitalController {
	
	private static final Logger logger = LoggerFactory.getLogger(HospitalController.class.getName());

	

	@Autowired
	HospitalService hospitalService;
		
	
	/**
	 * Function createHospital is mapped to /api/test/hospital/create for adding hospitals
	 * @param Hospital Represents the JSON data sent from the client via POST request
	 */
		@PostMapping("/create")
		public void createHospital(@RequestBody Hospital hospital) {
			try {
				hospitalService.createHospital(hospital);  
			}
			catch(RecordAlreadyExists e) { 
				return;
			}
			catch(ServerFailure e) {
				return;
			}
		     
		}

		/**
		 * Function getHospitals is mapped to /api/test/hospital/gethospital for getting all hospitals
		 * @return Returns List of all hospitals
		 */
		@GetMapping("/gethospital")
		public ResponseEntity<Object> getHospitals() {
			try {
				return new ResponseEntity<>(hospitalService.getHospitals(),HttpStatus.OK);
			}
			catch(RecordDoesNotExist e )
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
		 * Function getHospitalById is mapped to /api/test/hospital/{id} url pattern
		 * which fetches the details of the hospital for the given id 
		 * @param is id, which represents Hospital Id
		 * @return Hospital and its details
		 * 
		 */
		@GetMapping("/{id}")
		public ResponseEntity<Object> getHospitalById(@PathVariable int id) {
			try {
				return new ResponseEntity<>(hospitalService.getHospitalById(id),HttpStatus.OK);
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
