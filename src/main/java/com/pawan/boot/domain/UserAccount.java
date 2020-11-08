package com.pawan.boot.domain;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
@Data
public class UserAccount{

	
	private Integer userId;

	private String firstName;

	private String lastName;

	private String status;

	private String email;
	private String pwd;
	private String mobileNo;
    
	
	private String dob;

	private String gender;
	private Integer stateId;

	private Integer cityId;

	private Integer countryId;
	@CreationTimestamp
	private Date createdDate;
	@UpdateTimestamp
	private Date updatedDate;
}
