package com.letsCode.codingPlatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LeaderBoard {

    @Id
    private String userName;
    private int totalPoints;
    private int totalProblemsSolved;

    public LeaderBoard() {

    }
    public LeaderBoard(String userName, int totalPoints, int totalProblemsSolved, int totalSubmissions, int totalAcceptedSubmissions, int totalWrongSubmissions) {
        this.userName = userName;
        this.totalPoints = totalPoints;
        this.totalProblemsSolved = totalProblemsSolved;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getTotalPoints() {
        return totalPoints;
    }
    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
    public int getTotalProblemsSolved() {
        return totalProblemsSolved;
    }
    public void setTotalProblemsSolved(int totalProblemsSolved) {
        this.totalProblemsSolved = totalProblemsSolved;
    }

    @Override
    public String toString() {
        return "LeaderBoard [userName=" + userName + ", totalPoints=" + totalPoints + ", totalProblemsSolved="
                + totalProblemsSolved + "]";
    }
}
