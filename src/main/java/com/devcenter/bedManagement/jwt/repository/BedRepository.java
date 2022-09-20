package com.devcenter.bedManagement.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcenter.bedManagement.jwt.models.Bed;
import com.devcenter.bedManagement.jwt.models.Room;

@Repository
public interface BedRepository extends JpaRepository<Bed, Integer>{
	
	List<Bed> getBedsByRoom(Room room);

}
