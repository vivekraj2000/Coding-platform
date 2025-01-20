package com.letsCode.codingPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letsCode.codingPlatform.model.Problems;
import com.letsCode.codingPlatform.service.ProblemsService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CrudProblemsController {

    @Autowired
    ProblemsService problemsService;

    @PostMapping("/problems")
    public void createProblem(@RequestBody List<Problems> problems){
        problemsService.createProblem(problems);
    }

    @PutMapping("/problems")
    public void updateProblem(@RequestBody List<Problems> problems){
        problemsService.updateProblem(problems);
    }

    @DeleteMapping("/problems")
    public void deleteProblem(@RequestParam("problemId") int problemId){
        problemsService.deleteProblem(problemId);
    }

    @GetMapping("/problems")
    public ResponseEntity<List<Problems>> getAllProblems(){
        List<Problems> problemsList = problemsService.getAllProblems();
        return new ResponseEntity<>(problemsList, HttpStatus.OK);
    }

    @GetMapping("/problemById")
    public ResponseEntity<Problems> getProblemById(@RequestParam("problemId") int problemId){
        return new ResponseEntity<>(problemsService.getProblemById(problemId), HttpStatus.OK);
    }
    
}
