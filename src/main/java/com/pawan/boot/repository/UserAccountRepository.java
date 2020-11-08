package com.pawan.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawan.boot.entity.UserAccountEntity;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Integer> {

	public UserAccountEntity findByEmail(String email);

	public UserAccountEntity findByEmailAndPwd(String email, String pwd);

}
