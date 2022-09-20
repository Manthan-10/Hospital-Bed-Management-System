package com.devcenter.bedManagement.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcenter.bedManagement.jwt.models.Floor;
import com.devcenter.bedManagement.jwt.models.Hospital;
/**
 * Repository class
 * @author MN097789
 *
 */
@Repository
public interface FloorRepository extends JpaRepository<Floor, Integer>{
	
	
	List<Floor> getFloorsByHospital(Hospital hosp);
}
