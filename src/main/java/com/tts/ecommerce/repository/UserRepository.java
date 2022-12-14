package com.tts.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.tts.ecommerce.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);
}
