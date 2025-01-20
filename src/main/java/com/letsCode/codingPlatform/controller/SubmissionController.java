package com.letsCode.codingPlatform.controller;

import org.springframework.web.bind.annotation.RestController;

import com.letsCode.codingPlatform.enums.SubmissionStatusEnum;
import com.letsCode.codingPlatform.model.RunCode;
import com.letsCode.codingPlatform.model.SubmitCode;
import com.letsCode.codingPlatform.service.RunCodeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
public class SubmissionController {

    @Autowired
    RunCodeService runCodeService;
    
    @PostMapping("/runCode")
    public String runCode(@RequestBody RunCode runCode) {
        String result = runCodeService.executeCode(runCode);
        return result;
    }

    @PostMapping("/submitCode")
    public SubmitCode submitCode(@RequestBody RunCode runCode) {
        SubmitCode submitCode = runCodeService.submitCode(runCode);
        return submitCode;
    }

    @DeleteMapping("/submitCode")
    public void deleteSubmission(@RequestParam("submissionId") String submissionId) {
        runCodeService.deleteSubmissionBySubmissionId(submissionId);
    }

    @GetMapping("/submitCode")
    public List<SubmitCode> getSubmissions(@RequestParam("userName") String userName, @RequestParam("problemId") int problemId) {
        List<SubmitCode> submitCodeList = runCodeService.getSubmissionsByUserNameAndProblemId(userName, problemId);
        return submitCodeList;
    }

    @GetMapping("/isProblemSolved")
    public boolean isProblemSolved(@RequestParam("userName") String userName, @RequestParam("problemId") int problemId) {
        List<SubmitCode> submitCodeList = runCodeService.getSubmissionsByUserNameAndProblemId(userName, problemId);
        for (SubmitCode submitCode : submitCodeList) {
            if (submitCode.getStatus().equals(SubmissionStatusEnum.Accepted)) {
                return true;
            }
        }
        return false;
    }
}
