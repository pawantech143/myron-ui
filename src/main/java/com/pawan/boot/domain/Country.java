package com.pawan.boot.domain;

import java.util.List;

import com.pawan.boot.entity.StateMasterEntity;

import lombok.Data;
@Data
public class Country {

	

	private Integer countryId;
	private String countryName;
	private String countryCode;
	
	private List<StateMasterEntity> stateMaster;
}
