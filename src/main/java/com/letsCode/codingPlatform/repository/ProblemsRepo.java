package com.letsCode.codingPlatform.repository;
import com.letsCode.codingPlatform.model.Problems;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProblemsRepo extends JpaRepository<Problems, Integer> {

}
