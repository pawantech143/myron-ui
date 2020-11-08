package com.pawan.boot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="COUNTRY")
public class CounttryMasterEntity {

	@Id
	@GeneratedValue
	
	private Integer countryId;
	
	private String countryName;
	
	private String countryCode;
	
	
}
