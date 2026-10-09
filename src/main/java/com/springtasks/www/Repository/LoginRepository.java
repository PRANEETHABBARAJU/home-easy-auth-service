package com.springtasks.www.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springtasks.www.Entity.LoginEntity;

public interface LoginRepository extends JpaRepository<LoginEntity,Integer> {

	Optional<LoginEntity> findByUserName(String userName);
	 boolean existsByPhone(String phone);
}
