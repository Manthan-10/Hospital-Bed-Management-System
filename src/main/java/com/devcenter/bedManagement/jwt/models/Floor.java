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
@Table(name = "Floor")
public class Floor {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String floorNo;
	
	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="hospital_id", referencedColumnName="id")
    private Hospital hospital;
	
	
	@OneToMany(mappedBy="floor", cascade = CascadeType.ALL)
	private List<Room> rooms;
	
	
	public Floor() {
		super();
	}


	public Floor(int id, String floorNo, Hospital hospital) {
		super();
		this.id = id;
		this.floorNo = floorNo;
		this.hospital = hospital;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	



	

	public String getFloorNo() {
		return floorNo;
	}


	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}


	public Hospital getHospital() {
		return hospital;
	}


	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	
	
	
	

}
