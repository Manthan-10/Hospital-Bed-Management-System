package com.devcenter.bedManagement.jwt.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "bed_assignment", uniqueConstraints = {@UniqueConstraint( columnNames = "Bed_id"),@UniqueConstraint( columnNames = "Patient_id")})
public class BedAssignment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="Bed_id", referencedColumnName= "id")
	private Bed beds;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="Patient_id", referencedColumnName= "id")
	private Patient patient;
	
	public BedAssignment() {
		super();
	}

	public BedAssignment(int id, Bed beds, Patient patient) {
		super();
		this.id = id;
		this.beds = beds;
		this.patient = patient;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Bed getBeds() {
		return beds;
	}

	public void setBeds(Bed beds) {
		this.beds = beds;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	
	
	

	
	
	
	
}
