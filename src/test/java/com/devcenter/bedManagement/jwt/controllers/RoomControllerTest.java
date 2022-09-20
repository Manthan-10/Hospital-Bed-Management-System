package com.devcenter.bedManagement.jwt.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.devcenter.bedManagement.jwt.models.Floor;
import com.devcenter.bedManagement.jwt.models.Hospital;
import com.devcenter.bedManagement.jwt.models.Room;
import com.devcenter.bedManagement.jwt.repository.RoomRepository;
import com.devcenter.bedManagement.jwt.security.services.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RoomControllerTest {
	
	@MockBean
	RoomRepository roomRepository;
	
	@MockBean
	RoomService roomService;
	
	@Autowired
	RoomController roomController;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@BeforeEach
	public void setUp() {      
	    mockMvc = MockMvcBuilders.standaloneSetup(roomController).build(); 
	}
	
	@Test
	public void saveRoomSuccessTest() throws Exception{
		
		Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		Floor floor = new Floor(3, "Fifth", hosp1);
		 
		Room room = new Room(4, "677", floor);
		
		Mockito.doNothing().when(roomService).createRoom(Mockito.any(Room.class));
		
		RequestBuilder request = MockMvcRequestBuilders.post("/api/test/room/addroom")
                .content(asJsonString(room))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

			MvcResult mvcResult = mockMvc.perform(request).andReturn();
			Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
	
	
	@Test
	public void fetchAllRoomsSuccessTest() throws Exception{
		
		 Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		 Floor floor = new Floor(3, "Fifth", hosp1);
		 
		 List<Room> roomList = Arrays.asList(
	                new Room(1,"105", floor),
	                new Room(2, "567", floor),
	                new Room(3, "789", floor)
	        );
		 
		 when(roomService.getRooms()).thenReturn(roomList);

	        mockMvc.perform(
	                MockMvcRequestBuilders.get("/api/test/room/getallrooms"))
	                .andExpect(status().isOk())
	                .andExpect(content().json("[{}, {}, {}]"));
	}
	
	@Test
	public void fetchAllRoomsByFloorIdTest()  throws Exception{
		
		Hospital hosp1 = new Hospital(1, "tes1t", "test1","778");
		Floor floor = new Floor(3, "Fifth", hosp1);
		 
		 List<Room> roomList = Arrays.asList(
	                new Room(1,"105", floor),
	                new Room(2, "567", floor),
	                new Room(3, "789", floor)
	        );
		
		when(roomService.getRoomsByFloor(3)).thenReturn(roomList);
		
		mockMvc.perform(
                MockMvcRequestBuilders.get("/api/test/room/getbyfid/3"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}, {}, {}]"));
		
		
		
	}
	
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
