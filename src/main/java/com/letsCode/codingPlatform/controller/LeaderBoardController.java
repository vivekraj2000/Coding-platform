package com.letsCode.codingPlatform.controller;

import org.springframework.web.bind.annotation.RestController;

import com.letsCode.codingPlatform.model.LeaderBoard;
import com.letsCode.codingPlatform.service.LeaderBoradService;
import com.letsCode.codingPlatform.utils.SummaryApiUtils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class LeaderBoardController {
    private static final Logger logger = LoggerFactory.getLogger(LeaderBoardController.class);

    @Autowired
    SummaryApiUtils summaryApiUtils;

    @Autowired
    LeaderBoradService leaderBoradService;
    
    @GetMapping("/leaderBoard")
    public ResponseEntity<List<LeaderBoard>> getLeaderBoard(@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        long startTime = summaryApiUtils.startFunction();

        List<LeaderBoard> leaderBoardList = leaderBoradService.getLeaderBoard(offset, limit);
        if (leaderBoardList == null) {
            return new ResponseEntity<List<LeaderBoard>>(HttpStatus.NO_CONTENT);
        }

        logger.info("Time taken to fetch problems: " + summaryApiUtils.endFunction(startTime) + " seconds");
        return new ResponseEntity<List<LeaderBoard>>(leaderBoardList, HttpStatus.OK);
    }
}
