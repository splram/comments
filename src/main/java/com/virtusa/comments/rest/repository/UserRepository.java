package com.virtusa.comments.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.virtusa.comments.entities.User;
public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "select * from User u where u.email= :email", nativeQuery = true)
	public User findAllByEmail(@Param("email") String email);
	
}
