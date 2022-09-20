package com.devcenter.bedManagement.jwt.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcenter.bedManagement.jwt.exception.RecordDoesNotExist;
import com.devcenter.bedManagement.jwt.exception.ServerFailure;
import com.devcenter.bedManagement.jwt.helper.Constants;
import com.devcenter.bedManagement.jwt.repository.PatientRepository;
import com.devcenter.bedManagement.jwt.security.services.PatientService;

/**
 * Controller
 * @author MN097789
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class PatientController {
	
	private static final Logger logger = LoggerFactory.getLogger(PatientController.class.getName());
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	PatientService patientService;
	

	/**
	 * Function findAllPatients is mapped to /api/test/patients for getting all the patients
	 * @return Returns List of all patients
	 */
	@GetMapping("/patients")
	public ResponseEntity<Object> findAllPatients() {
		
		try {
			return new ResponseEntity<>(patientService.getPatients(),HttpStatus.OK); 
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
	 * Function getPatientsByHospital is mapped to /api/test//patients/byhospitalid/{hid} url pattern
	 * which fetches the details of patients for the given hospital id 
	 * @param is hid, which represents Bed Hospital id
	 * @return The patients in that particular hospital and its details
	 * 
	 */
	@GetMapping("/patients/byhospitalid/{hid}")
	public ResponseEntity<Object> getPatientsByHospital(@PathVariable int hid) {
		
		try {
			return new ResponseEntity<>(patientService.getPatientsByHospital(hid),HttpStatus.OK);
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
			

}
