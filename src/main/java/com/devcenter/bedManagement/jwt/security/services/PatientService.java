package com.devcenter.bedManagement.jwt.security.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcenter.bedManagement.jwt.models.Hospital;
import com.devcenter.bedManagement.jwt.models.Patient;
import com.devcenter.bedManagement.jwt.repository.HospitalRepository;
import com.devcenter.bedManagement.jwt.repository.PatientRepository;
/**
 * Service class
 * @author MN097789
 *
 */
@Service
public class PatientService {
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	HospitalRepository hospitalRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * Function fetches all the patients
	 * @return Returns List of all patients
	 */
	public List<Patient> getPatients() {
	    return patientRepository.findAll(); 
	}
	
	
	/**
	 * Function fetches the details of patients for the given hospital id 
	 * @param is hid, which represents Bed Hospital id
	 * @return The patients in that particular hospital and its details
	 * 
	 */
	public List<Patient> getPatientsByHospital(Integer id) {
			
			Optional<Hospital> hosp = hospitalRepository.findById(id); 
			
			List<Patient> patients = patientRepository.getPatientsByHospital(this.modelMapper.map(hosp, Hospital.class));  
			
			return patients; 
			
			
		}

}
