package com.devcenter.bedManagement.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.devcenter.bedManagement.jwt.models.Bed;
import com.devcenter.bedManagement.jwt.models.BedAssignment;
import com.devcenter.bedManagement.jwt.models.Patient;
/**
 * Repository class
 * @author MN097789
 *
 */
@Repository
public interface BedAssignmentRepository extends JpaRepository<BedAssignment, Integer>{
	
	BedAssignment getBedAssignmentByBeds(Bed beds);
	BedAssignment getBedAssignmentByPatient(Patient patient);
	
	@Transactional
	public void deleteBedAssignmentByPatient(Patient patient);
	
	
}
