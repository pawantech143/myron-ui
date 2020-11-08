package com.pawan.boot.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="STATE")
public class StateMasterEntity{

	@Id
	@GeneratedValue
	
	private Integer stateId;
	
	private String stateName;
	
	private Integer countryId;
	
	
	
}
