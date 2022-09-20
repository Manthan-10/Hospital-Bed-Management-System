package com.devcenter.bedManagement.jwt.security.services;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.devcenter.bedManagement.jwt.models.Floor;
import com.devcenter.bedManagement.jwt.models.Hospital;
import com.devcenter.bedManagement.jwt.models.Room;
import com.devcenter.bedManagement.jwt.repository.RoomRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomServiceTest {
	
	@MockBean
	RoomRepository roomRepository;
	
	@MockBean
	RoomService roomService; 
	
	@Test
    void SaveRoomSuccessTest(){
		
		Hospital hosp = new Hospital(1, "test", "test","7778");
		
		Floor floor = new Floor(3, "Fifth", hosp);
		
		Room room = new Room(1, "145", floor);
		
        Mockito.when(roomRepository.save(Mockito.any(Room.class))).thenReturn(room);
        Assertions.assertEquals(room, roomRepository.save(room)); 
    }
	
	@Test
	void FetchAllRoomsSuccessTest() {
		
		 Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		 Floor floor = new Floor(3, "Fifth", hosp1);
		 
		 List<Room> roomList = Arrays.asList(
	                new Room(1,"105", floor),
	                new Room(2, "567", floor),
	                new Room(3, "789", floor)
	        );
		
		Mockito.when(roomRepository.findAll()).thenReturn(roomList);
		Assertions.assertEquals(roomList, roomRepository.findAll());
		
	}
	
	@Test 
	public void FetchAllFloorsByHIdTest() {
		
		Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		Floor floor = new Floor(3, "Fifth", hosp1);
		 
		 List<Room> roomList = Arrays.asList(
	                new Room(1,"105", floor),
	                new Room(2, "567", floor),
	                new Room(3, "789", floor)
	        );
		
		Mockito.when(roomRepository.getRoomsByFloor(floor)).thenReturn(roomList);
		Assertions.assertEquals(roomList, roomRepository.getRoomsByFloor(floor));
		
		
		
	}

}
