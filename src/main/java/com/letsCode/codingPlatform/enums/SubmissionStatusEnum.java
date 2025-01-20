package com.letsCode.codingPlatform.enums;

public enum SubmissionStatusEnum {
    Accepted("Accepted"), Rejected("Rejected");

    private String submissionStatus;

    SubmissionStatusEnum(String submissionStatus) {
        this.submissionStatus = submissionStatus;
    }

    public String getSubmissionStatusString() {
        return submissionStatus;
    }

    @Override
    public String toString() {
        return String.valueOf(submissionStatus);
    }
}
