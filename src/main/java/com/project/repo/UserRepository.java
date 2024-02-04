package com.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
	User findByEmail (String email);
}
