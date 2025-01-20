package com.letsCode.codingPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letsCode.codingPlatform.model.LeaderBoard;

@Repository
public interface LeaderBoardRepo extends JpaRepository<LeaderBoard, String> {
    
}
