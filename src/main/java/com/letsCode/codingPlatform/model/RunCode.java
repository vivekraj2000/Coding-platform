package com.letsCode.codingPlatform.model;

public class RunCode {
    private int problemId;
    private String language;
    private String code;
    private String userName;
    
    public RunCode() {
        
    }
    public RunCode(int problemId, String language, String code, String userName) {
        this.problemId = problemId;
        this.language = language;
        this.code = code;
        this.userName = userName;
    }

    public int getProblemId() {
        return problemId;
    }
    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "RunCode [problemId=" + problemId + ", language=" + language + ", code=" + code +  ", userName=" + userName +"]";
    }
}
