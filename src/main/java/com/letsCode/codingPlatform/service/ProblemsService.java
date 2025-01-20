package com.letsCode.codingPlatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letsCode.codingPlatform.enums.UserRoleEnum;
import com.letsCode.codingPlatform.model.Problems;
import com.letsCode.codingPlatform.repository.ProblemsRepo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProblemsService {

    private static final Logger log = LoggerFactory.getLogger(ProblemsService.class);

    @Autowired
    ProblemsRepo problemsRepo;
    
    @Autowired
    UsersService usersService;
    
    public String createProblem(List<Problems> problems) {
        log.info("Creating a new problem");
        UserRoleEnum userRole = usersService.getUserByUserName(problems.get(0).getUserName()).getRole();
        if (userRole != UserRoleEnum.ADMIN) {
            throw new RuntimeException("Only admins can create problems");
        }
        problemsRepo.saveAll(problems);
        return "Successfully added problem";
    }

    public void updateProblem(List<Problems> problems) {
        log.info("Updating a problem");
        problemsRepo.saveAll(problems);
    }

    public void deleteProblem(int problemId){
        log.info("Deleting a problem");
        problemsRepo.deleteById(problemId);
    }

    public List<Problems> getAllProblems(){
        log.info("Getting all problems");
        return problemsRepo.findAll();
    }

    public List<Problems> getAllProblems(Integer offset, Integer limit) {
        log.info("Getting all problems");
        List<Problems> problemsList =  problemsRepo.findAll();
        problemsList.sort((p1, p2) -> Integer.compare(p1.getProblemId(), p2.getProblemId()));
        if (offset != null && limit != null) {
            if (offset < problemsList.size()) {
                if (offset + limit < problemsList.size()) {
                    problemsList = problemsList.subList(offset, offset + limit);
                } else {
                    problemsList = problemsList.subList(offset, problemsList.size());
                }
            } else {
                problemsList = null;
            }
        }
        return problemsList;
    }

    public Problems getProblemById(int problemId) {
        log.info("Getting problem by id " + problemId);
        return problemsRepo.findById(problemId).orElse(null);
    }
}
