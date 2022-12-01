package com.ssafy.ReviewMoa_Spring.repository;


import com.ssafy.ReviewMoa_Spring.dao.testUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface testRepository extends JpaRepository<testUser, Long> {

}