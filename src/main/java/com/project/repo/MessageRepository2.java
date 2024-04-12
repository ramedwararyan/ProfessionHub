package com.project.repo;


import com.project.entities.DsMessage;
import com.project.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository2 extends JpaRepository<DsMessage, Long> {
    // Additional methods if needed
}

