package com.devcenter.bedManagement.jwt.security.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.devcenter.bedManagement.jwt.models.Bed;
import com.devcenter.bedManagement.jwt.models.BedAssignment;
import com.devcenter.bedManagement.jwt.models.Patient;
import com.devcenter.bedManagement.jwt.repository.BedAssignmentRepository;
import com.devcenter.bedManagement.jwt.repository.BedRepository;
import com.devcenter.bedManagement.jwt.repository.PatientRepository;

/**
 * Service class
 * @author MN097789
 *
 */
@Service
public class BedAssignmentService {
	
	
	@Autowired
	BedAssignmentRepository bedAssignmentRepository;
	
	@Autowired
	BedRepository bedRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	/**
	 * Function assigns a bed to patient
	 * @param bedAssignment represents BedAssignment object
	 */
	public void createPatientBed(BedAssignment bedAssignment) {
		 bedAssignmentRepository.save(bedAssignment);    
	}
	
	

	/**
	 * Function fetches all bed assignments
	 * @return Returns List of all bed assignments
	 */
	public List<BedAssignment> getPatientBed() {
		return bedAssignmentRepository.findAll();
	}
	
	
	/**
	 * Function fetches the details of bed assignment for the given bed id 
	 * @param is id, which represents Bed Id
	 * @return The Bed Assignment in that particular Bed and its details
	 * 
	 */
	public BedAssignment getBedAssignmentByBeds(int id) {
		
		Optional<Bed> bed = bedRepository.findById(id);
		BedAssignment bedAssignments = bedAssignmentRepository.getBedAssignmentByBeds(this.modelMapper.map(bed, Bed.class));
		
		return bedAssignments; 
		
	}
	
	
	/**
	 * Function fetches the details of bed assignment for the given patient id 
	 * @param is id, which represents Patient Id
	 * @return The Bed Assignment for that particular Patient and its details
	 * 
	 */
	public BedAssignment getBedAssignmentByPatient(int id) {
		
		Optional<Patient> patient = patientRepository.findById(id);
		BedAssignment bedAssignments = bedAssignmentRepository.getBedAssignmentByPatient(this.modelMapper.map(patient,  Patient.class));
		
		return bedAssignments;
	}
	
	/**
	 * Function deletes the  bed assignment for the given patient id 
	 * @param is id, which represents Patient Id
	 */
	public void deleteBedAssignmentByPatient(int id) {
		Optional<Patient> patient = patientRepository.findById(id); 
		bedAssignmentRepository.deleteBedAssignmentByPatient(this.modelMapper.map(patient,  Patient.class));
		
	}
	
}
