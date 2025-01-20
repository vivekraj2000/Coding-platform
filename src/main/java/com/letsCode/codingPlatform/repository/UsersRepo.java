package com.letsCode.codingPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letsCode.codingPlatform.model.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, String> {
    
}
