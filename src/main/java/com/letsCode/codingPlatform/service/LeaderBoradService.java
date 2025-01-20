package com.letsCode.codingPlatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letsCode.codingPlatform.model.LeaderBoard;
import com.letsCode.codingPlatform.repository.LeaderBoardRepo;

@Service
public class LeaderBoradService {

    @Autowired
    LeaderBoardRepo leaderBoardRepo;

    public List<LeaderBoard> getLeaderBoard(Integer offset, Integer limit) {
        List<LeaderBoard> leaderBoardList =  leaderBoardRepo.findAll();
        leaderBoardList.sort((u1, u2) -> Integer.compare(u2.getTotalPoints(), u1.getTotalPoints()));
        if (offset != null && limit != null) {
            if (offset < leaderBoardList.size()) {
                if (offset + limit < leaderBoardList.size()) {
                    leaderBoardList = leaderBoardList.subList(offset, offset + limit);
                } else {
                    leaderBoardList = leaderBoardList.subList(offset, leaderBoardList.size());
                }
            } else {
                leaderBoardList = null;
            }
        }
        return leaderBoardList;
    }

    public void deleteUserInLeaderBoard(String userName) {
        leaderBoardRepo.deleteById(userName);
    }
}
