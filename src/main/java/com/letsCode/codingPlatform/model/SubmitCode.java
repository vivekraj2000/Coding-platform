package com.letsCode.codingPlatform.model;

import com.letsCode.codingPlatform.enums.SubmissionStatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class SubmitCode {
    @Id
    private String submissionId;
    private int problemId;
    private String userName;
    private String language;
    private String code;

    @Enumerated(EnumType.STRING)
    private SubmissionStatusEnum status;
    private String executionTime;
    private String submittedAt;

    public SubmitCode() {

    }
    public SubmitCode(String submissionId, int problemId, String userName, String language, String code, SubmissionStatusEnum status,
            String executionTime, String submittedAt) {
        this.submissionId = submissionId;
        this.problemId = problemId;
        this.userName = userName;
        this.language = language;
        this.code = code;
        this.status = status;
        this.executionTime = executionTime;
        this.submittedAt = submittedAt;
    }
    public String getSubmissionId() {
        return submissionId;
    }
    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }
    public int getProblemId() {
        return problemId;
    }
    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
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
    public SubmissionStatusEnum getStatus() {
        return status;
    }
    public void setStatus(SubmissionStatusEnum status) {
        this.status = status;
    }
    public String getExecutionTime() {
        return executionTime;
    }
    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }
    public String getSubmittedAt() {
        return submittedAt;
    }
    public void setSubmittedAt(String submittedAt) {
        this.submittedAt = submittedAt;
    }
    @Override
    public String toString() {
        return "SubmitCode [submissionId=" + submissionId + ", problemId=" + problemId + ", userName=" + userName
                + ", language=" + language + ", code=" + code + ", status=" + status + ", executionTime="
                + executionTime + ", submittedAt=" + submittedAt + "]";
    }
}
