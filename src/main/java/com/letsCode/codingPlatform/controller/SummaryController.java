package com.letsCode.codingPlatform.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letsCode.codingPlatform.model.Problems;
import com.letsCode.codingPlatform.service.ProblemsService;
import com.letsCode.codingPlatform.service.RunCodeService;
import com.letsCode.codingPlatform.utils.SummaryApiUtils;

@RestController
@RequestMapping("/api")
public class SummaryController {
    private static final Logger logger = LoggerFactory.getLogger(SummaryController.class);

    @Autowired
    ProblemsService problemsService;

    @Autowired
    SummaryApiUtils summaryApiUtils;

    @Autowired
    RunCodeService runCodeService;
    
    @GetMapping("/problemsSummary")
    public ResponseEntity<List<Problems>> getAllProblems(@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        long startTime = summaryApiUtils.startFunction();

        List<Problems> problemsList = problemsService.getAllProblems(offset, limit);
        if (problemsList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        logger.info("Time taken to fetch problems: " + summaryApiUtils.endFunction(startTime) + " seconds");
        return new ResponseEntity<>(problemsList, HttpStatus.OK);
    }

    @GetMapping("/problemSummary")
    public ResponseEntity<Problems> getProblemById(@RequestParam("problemId") int problemId) {
        long startTime = summaryApiUtils.startFunction();

        Problems problem = problemsService.getProblemById(problemId);
        logger.info("Time taken to fetch problem: " + summaryApiUtils.endFunction(startTime) + " seconds");
        return new ResponseEntity<>(problem, HttpStatus.OK);
    }
}
