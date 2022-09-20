package com.devcenter.bedManagement.jwt.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Room")
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String roomNo;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="Floor_id", referencedColumnName= "id")
	private Floor floor;
	
	@OneToMany(mappedBy="room", cascade = CascadeType.ALL)
	private List<Bed> beds;
	
	
	
	public Room() {
		super();
	}



	public Room(int id, String roomNo, Floor floor) {
		super();
		this.id = id;
		this.roomNo = roomNo;
		this.floor = floor;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getRoomNo() {
		return roomNo;
	}



	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}



	public Floor getFloor() {
		return floor;
	}



	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	
	
}
