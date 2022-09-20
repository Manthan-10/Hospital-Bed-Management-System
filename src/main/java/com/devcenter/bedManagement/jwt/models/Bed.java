package com.devcenter.bedManagement.jwt.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Bed")
public class Bed {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String bedNo;
	
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="Room_id", referencedColumnName= "id")
	private Room room;
	
	
	@OneToOne(mappedBy="beds", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private BedAssignment bedAssignment;
	
	public Bed() {
		super();
	}

	public Bed(int id, String bedNo, Room room) {
		super();
		this.id = id;
		this.bedNo = bedNo; 
		this.room = room;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBedNo() {
		return bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	
	

}
