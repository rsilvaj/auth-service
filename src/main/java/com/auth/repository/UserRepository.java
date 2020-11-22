package com.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.auth.model.User;

@Component
public interface UserRepository extends BaseRepository<User, Long> {
	
	@Query("select u from User u where u.email = :email ")
	public Optional<User> findByEmail(String email);

}
