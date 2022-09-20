package com.devcenter.bedManagement.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devcenter.bedManagement.jwt.models.Floor;
import com.devcenter.bedManagement.jwt.models.Room;
/**
 * Repository class
 * @author MN097789
 *
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>{
	
	List<Room> getRoomsByFloor(Floor floor);

}
