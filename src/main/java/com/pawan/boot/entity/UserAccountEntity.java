package com.pawan.boot.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
public class UserAccountEntity {
	@Id
	@GeneratedValue
	private Integer userId;

	private String firstName;

	private String lastName;

	private String status;

	private String email;
	private String pwd;
	private String mobileNo;

	private Date dob;

	private Integer stateId;

	private Integer cityId;

	private Integer countryId;
	@CreationTimestamp
	private Date createdDate;
	@UpdateTimestamp
	private Date updatedDate;

}
