package com.nmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nmt.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findOneByUserNameAndStatus(String userName, Integer status);
}
