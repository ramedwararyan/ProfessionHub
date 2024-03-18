package com.project.repo;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
	User findByEmail (String email);
	@Query("select u from User u where u.email =:email")
	public User getUserByUserName(@Param("email") String email);
	 
	User findByFullname(String fullname);


}
