package com.devcenter.bedManagement.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.devcenter.bedManagement.jwt.models.Hospital;
import com.devcenter.bedManagement.jwt.models.Patient;
/**
 * Repository class
 * @author MN097789
 *
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{
	
	
	
	List<Patient> getPatientsByHospital(Hospital hosp);
	

}
