package com.pawan.boot.domain;

import java.util.List;

import com.pawan.boot.entity.CityMasterEntity;

import lombok.Data;


@Data
public class States {

	private Integer stateId;
	private String stateName;
	
	private List<CityMasterEntity> cityMaster;
}
