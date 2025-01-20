package com.letsCode.codingPlatform.enums;

public enum ProblemDifficultyEnum {
    Easy("Easy"), Medium("Medium"), Hard("Hard");

    private String difficulty;

    ProblemDifficultyEnum(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficultyString() {
        return difficulty;
    }

    @Override
    public String toString() {
        return String.valueOf(difficulty);
    }
}
