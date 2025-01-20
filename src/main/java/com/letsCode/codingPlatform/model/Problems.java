package com.letsCode.codingPlatform.model;

import java.util.List;

import com.letsCode.codingPlatform.enums.ProblemDifficultyEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class Problems {
    @Id
    private int problemId;
    private String userName;
    private String title;
    private String description;
    
    @Enumerated(EnumType.STRING)
    private ProblemDifficultyEnum difficulty;
    private String constraints;
    private List<String> examples;
    private String topics;
    private List<String> hints;
    private String cTemplateCode;
    private String cMainCode;
    private String cAllTestCasesCode;
    private String cppTemplateCode;
    private String cppMainCode;
    private String cppAllTestCasesCode;
    private String javaTemplateCode;
    private String javaMainCode;
    private String javaAllTestCasesCode;
    private String pythonTemplateCode;
    private String pythonMainCode;
    private String pythonAllTestCasesCode;

    public Problems() {

    }
    public Problems(int problemId, String userName, String title, String description, ProblemDifficultyEnum difficulty, String constraints,
            List<String> examples, String topics, List<String> hints, String cTemplateCode, String cMainCode,
            String cAllTestCasesCode, String cppTemplateCode, String cppMainCode, String cppAllTestCasesCode,
            String javaTemplateCode, String javaMainCode, String javaAllTestCasesCode, String pythonTemplateCode,
            String pythonMainCode, String pythonAllTestCasesCode) {
        this.problemId = problemId;
        this.userName = userName;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.constraints = constraints;
        this.examples = examples;
        this.topics = topics;
        this.hints = hints;
        this.cTemplateCode = cTemplateCode;
        this.cMainCode = cMainCode;
        this.cAllTestCasesCode = cAllTestCasesCode;
        this.cppTemplateCode = cppTemplateCode;
        this.cppMainCode = cppMainCode;
        this.cppAllTestCasesCode = cppAllTestCasesCode;
        this.javaTemplateCode = javaTemplateCode;
        this.javaMainCode = javaMainCode;
        this.javaAllTestCasesCode = javaAllTestCasesCode;
        this.pythonTemplateCode = pythonTemplateCode;
        this.pythonMainCode = pythonMainCode;
        this.pythonAllTestCasesCode = pythonAllTestCasesCode;
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
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public ProblemDifficultyEnum getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(ProblemDifficultyEnum difficulty) {
        this.difficulty = difficulty;
    }
    public String getConstraints() {
        return constraints;
    }
    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }
    public List<String> getExamples() {
        return examples;
    }
    public void setExamples(List<String> examples) {
        this.examples = examples;
    }
    public String getTopics() {
        return topics;
    }
    public void setTopics(String topics) {
        this.topics = topics;
    }
    public List<String> getHints() {
        return hints;
    }
    public void setHints(List<String> hints) {
        this.hints = hints;
    }
    public String getcTemplateCode() {
        return cTemplateCode;
    }
    public void setcTemplateCode(String cTemplateCode) {
        this.cTemplateCode = cTemplateCode;
    }
    public String getcMainCode() {
        return cMainCode;
    }
    public void setcMainCode(String cMainCode) {
        this.cMainCode = cMainCode;
    }
    public String getcAllTestCasesCode() {
        return cAllTestCasesCode;
    }
    public void setcAllTestCasesCode(String cAllTestCasesCode) {
        this.cAllTestCasesCode = cAllTestCasesCode;
    }
    public String getCppTemplateCode() {
        return cppTemplateCode;
    }
    public void setCppTemplateCode(String cppTemplateCode) {
        this.cppTemplateCode = cppTemplateCode;
    }
    public String getCppMainCode() {
        return cppMainCode;
    }
    public void setCppMainCode(String cppMainCode) {
        this.cppMainCode = cppMainCode;
    }
    public String getCppAllTestCasesCode() {
        return cppAllTestCasesCode;
    }
    public void setCppAllTestCasesCode(String cppAllTestCasesCode) {
        this.cppAllTestCasesCode = cppAllTestCasesCode;
    }
    public String getJavaTemplateCode() {
        return javaTemplateCode;
    }
    public void setJavaTemplateCode(String javaTemplateCode) {
        this.javaTemplateCode = javaTemplateCode;
    }
    public String getJavaMainCode() {
        return javaMainCode;
    }
    public void setJavaMainCode(String javaMainCode) {
        this.javaMainCode = javaMainCode;
    }
    public String getJavaAllTestCasesCode() {
        return javaAllTestCasesCode;
    }
    public void setJavaAllTestCasesCode(String javaAllTestCasesCode) {
        this.javaAllTestCasesCode = javaAllTestCasesCode;
    }
    public String getPythonTemplateCode() {
        return pythonTemplateCode;
    }
    public void setPythonTemplateCode(String pythonTemplateCode) {
        this.pythonTemplateCode = pythonTemplateCode;
    }
    public String getPythonMainCode() {
        return pythonMainCode;
    }
    public void setPythonMainCode(String pythonMainCode) {
        this.pythonMainCode = pythonMainCode;
    }
    public String getPythonAllTestCasesCode() {
        return pythonAllTestCasesCode;
    }
    public void setPythonAllTestCasesCode(String pythonAllTestCasesCode) {
        this.pythonAllTestCasesCode = pythonAllTestCasesCode;
    }
    @Override
    public String toString() {
        return "Problems [problemId=" + problemId + ", userName=" + userName +", title=" + title + ", description=" + description
                + ", difficulty=" + difficulty + ", constraints=" + constraints + ", examples=" + examples + ", topics="
                + topics + ", hints=" + hints + ", cTemplateCode=" + cTemplateCode + ", cMainCode=" + cMainCode
                + ", cAllTestCasesCode=" + cAllTestCasesCode + ", cppTemplateCode=" + cppTemplateCode + ", cppMainCode="
                + cppMainCode + ", cppAllTestCasesCode=" + cppAllTestCasesCode + ", javaTemplateCode="
                + javaTemplateCode + ", javaMainCode=" + javaMainCode + ", javaAllTestCasesCode=" + javaAllTestCasesCode
                + ", pythonTemplateCode=" + pythonTemplateCode + ", pythonMainCode=" + pythonMainCode
                + ", pythonAllTestCasesCode=" + pythonAllTestCasesCode + "]";
    }
}
