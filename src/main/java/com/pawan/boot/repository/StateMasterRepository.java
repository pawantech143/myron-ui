package com.pawan.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pawan.boot.entity.StateMasterEntity;

public interface StateMasterRepository extends JpaRepository<StateMasterEntity, Integer>{

	
	public List<StateMasterEntity> findByCountryId(Integer countryId);
}
