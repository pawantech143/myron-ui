package com.pawan.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawan.boot.entity.CityMasterEntity;

public interface CityMasterRepository extends JpaRepository<CityMasterEntity, Integer> {

	public List<CityMasterEntity> findByStateId(Integer stateId);
}
