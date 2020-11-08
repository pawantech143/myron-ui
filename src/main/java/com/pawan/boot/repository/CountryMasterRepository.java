package com.pawan.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawan.boot.entity.CounttryMasterEntity;

public interface CountryMasterRepository extends JpaRepository<CounttryMasterEntity, Integer> {

}
