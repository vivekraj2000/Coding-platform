package com.letsCode.codingPlatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.letsCode.codingPlatform.model.SubmitCode;

@Repository
public interface SubmissionsRepo extends JpaRepository<SubmitCode, String> {

    @Query("SELECT s FROM SubmitCode s WHERE s.userName = ?1 AND s.problemId = ?2")
    List<SubmitCode> findByUserNameAndProblemId(String userName, int problemId);

    @Query("SELECT s FROM SubmitCode s WHERE s.userName = ?1")
    List<SubmitCode> findByUserName(String userName);
    
}
