package com.devcenter.bedManagement.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcenter.bedManagement.jwt.models.Hospital;
/**
 * Repository class
 * @author MN097789
 *
 */
@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer>{
	


}
