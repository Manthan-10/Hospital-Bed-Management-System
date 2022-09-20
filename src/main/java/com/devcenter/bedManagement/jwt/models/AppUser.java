package com.devcenter.bedManagement.jwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
    })
//Represents user entity class
public class AppUser {
	  @Id
	  @NotBlank
	  @Size(max = 3)
	  private String id;

	  @NotBlank
	  @Size(max = 20)
	  private String username;

	  @NotBlank
	  @Size(max = 120)
	  private String password;
	  
	  public AppUser() {
	  }

	  public AppUser(String id, String username, String password) { 
		this.id=id;  
	    this.username = username; 
	    this.password = password;
	  }

	  public String getId() {
	    return id;
	  }

	  public void setId(String id) {
	    this.id = id;
	  }

	  public String getUsername() {
	    return username;
	  }

	  public void setUsername(String username) {
	    this.username = username;
	  }

	  public String getPassword() {
	    return password;
	  }

	  public void setPassword(String password) {
	    this.password = password;
	  }

}
