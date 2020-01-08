package com.example.simpleservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityDao extends JpaRepository<SimpEntity, Long> {

}
