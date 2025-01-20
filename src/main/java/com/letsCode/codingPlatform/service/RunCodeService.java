package com.letsCode.codingPlatform.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letsCode.codingPlatform.enums.ProblemDifficultyEnum;
import com.letsCode.codingPlatform.enums.SubmissionStatusEnum;
import com.letsCode.codingPlatform.model.LeaderBoard;
import com.letsCode.codingPlatform.model.Problems;
import com.letsCode.codingPlatform.model.RunCode;
import com.letsCode.codingPlatform.model.SubmitCode;
import com.letsCode.codingPlatform.repository.LeaderBoardRepo;
import com.letsCode.codingPlatform.repository.ProblemsRepo;
import com.letsCode.codingPlatform.repository.SubmissionsRepo;
import com.letsCode.codingPlatform.utils.SummaryApiUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RunCodeService {

    private static final Logger logger = LoggerFactory.getLogger(RunCodeService.class);

    @Autowired
    ProblemsService problemsService;

    @Autowired
    SummaryApiUtils summaryApiUtils;

    @Autowired
    SubmissionsRepo submissionsRepo;

    @Autowired
    LeaderBoardRepo leaderBoardRepo;

    @Autowired
    ProblemsRepo problemsRepo;

    private static String extractClassName(String code) {
        Pattern pattern = Pattern.compile("public\\s+class\\s+(\\w+)");
        Matcher matcher = pattern.matcher(code);
        if (matcher.find()) {
            return matcher.group(1); // Return the class name
        }
        return null; // If no class is found
    }
    
    public String executeCode(RunCode runCode) {
        String code = runCode.getCode();
        String language = runCode.getLanguage();
        logger.info("executing code");
        String result = "";

        try {
            // Determine file extension and commands based on the language
            String extension = "";
            String compileCommand = "";
            String runCommand = "";
            String fileName = "";

            switch (language.toLowerCase()) {
                case "c":
                    extension = ".c";
                    fileName = "temp_c_";
                    compileCommand = "gcc";
                    runCommand = "./a.out"; // default name for gcc output
                    break;
                case "cpp":
                    extension = ".cpp";
                    fileName = "temp_cpp_";
                    compileCommand = "g++";
                    runCommand = "./a.out"; // default name for g++ output
                    break;
                case "java":
                    extension = ".java";
                    fileName = "temp_java_";
                    compileCommand = "javac";
                    runCommand = "java";
                    break;
                case "python3":
                    extension = ".py";
                    fileName = "temp_python_";
                    compileCommand = "";
                    runCommand = "python3";
                    break;
                default:
                    return "Unsupported language!";
            }

            // Save code to a temporary file with the appropriate extension
            File tempFile = File.createTempFile(fileName, extension);
            
            tempFile.deleteOnExit();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                writer.write(code);
            }

            // Compile the code if necessary (Java, C, C++)
            if (language.equalsIgnoreCase("java")) {
                String className = extractClassName(code);
                if (className == null) {
                    return "Error: No public class found in Java code.";
                }
                // Save code to a temporary file with the class name
                tempFile = new File(System.getProperty("java.io.tmpdir"), className + ".java");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                    writer.write(code);
                }
                tempFile.deleteOnExit();

                ProcessBuilder compileProcessBuilder = new ProcessBuilder(compileCommand, tempFile.getAbsolutePath());
                compileProcessBuilder.redirectErrorStream(true);
                logger.info("Compiling Java code with command: " + String.join(" ", compileProcessBuilder.command()));
                Process compileProcess = compileProcessBuilder.start();

                // Capture and print the error output from the compilation process
                try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()))) {
                    String line;
                    StringBuilder errorOutput = new StringBuilder();
                    while ((line = errorReader.readLine()) != null) {
                        errorOutput.append(line).append("\n");
                    }
                    if (errorOutput.length() > 0) {
                        logger.info("Compilation error output: " + errorOutput.toString());
                    }
                }

                int compileExitCode = compileProcess.waitFor();
                if (compileExitCode != 0) {
                    return "Error compiling Java code. Exit code: " + compileExitCode;
                }
                runCommand = "java " +tempFile.getAbsolutePath(); // For Java, the class file is executed by its name
                logger.info("Running Java code with command: " + runCommand);
            } else if (language.equalsIgnoreCase("c") || language.equalsIgnoreCase("cpp")) {
                String compiledFilePath = tempFile.getAbsolutePath().replace(extension, "");
                ProcessBuilder compileProcessBuilder = new ProcessBuilder(compileCommand, tempFile.getAbsolutePath(), "-o", compiledFilePath);
                compileProcessBuilder.redirectErrorStream(true);
                Process compileProcess = compileProcessBuilder.start();
                int compileExitCode = compileProcess.waitFor();
                if (compileExitCode != 0) {
                    return "Error compiling " + language.toUpperCase() + " code. Exit code: " + compileExitCode;
                }
                runCommand = compiledFilePath;
            }

            // Run the code (compiled or interpreted)
            ProcessBuilder runProcessBuilder = null;
            if (language.equals("python3")) {
                runProcessBuilder = new ProcessBuilder("python3", tempFile.getAbsolutePath());
            } else {
                runProcessBuilder = new ProcessBuilder(runCommand.split(" "));
            }
            if (language.equalsIgnoreCase("java")) {
                runProcessBuilder.directory(new File(System.getProperty("java.io.tmpdir")));
            }
            runProcessBuilder.redirectErrorStream(true);
            Process runProcess = runProcessBuilder.start();

            // Capture the output of the executed program
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()))) {
                String line;
                StringBuilder output = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
                result = output.toString();
            }

            // Capture and print the error output from the runtime process
            try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()))) {
                String line;
                StringBuilder errorOutput = new StringBuilder();
                while ((line = errorReader.readLine()) != null) {
                    errorOutput.append(line).append("\n");
                }
                if (errorOutput.length() > 0) {
                    logger.info("Runtime error output: " + errorOutput.toString());
                }
            }

            // Wait for the program to finish and check the exit code
            int runExitCode = runProcess.waitFor();
            if (runExitCode != 0) {
                result = "Error executing " + language.toUpperCase() + " code. Exit code: " + runExitCode;
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            result = "Error: " + e.getMessage();
        }

        return result;
    }

    public SubmitCode submitCode(RunCode runCode) {
        long startTime = summaryApiUtils.startFunction();
        String result = executeCode(runCode);
        String executionTime = String.valueOf(summaryApiUtils.endFunction(startTime));

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        SubmitCode submitCode = new SubmitCode();

        submitCode.setUserName(runCode.getUserName());
        submitCode.setSubmissionId(runCode.getUserName()+now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        submitCode.setProblemId(runCode.getProblemId());
        submitCode.setLanguage(runCode.getLanguage());
        submitCode.setCode(runCode.getCode());
        submitCode.setExecutionTime(executionTime);
        submitCode.setSubmittedAt(formattedDateTime);
        if (result.contains("True")) {
            submitCode.setStatus(SubmissionStatusEnum.Accepted);

            LeaderBoard leaderBoard = new LeaderBoard();
            LeaderBoard leaderBoardExisting = leaderBoardRepo.findById(runCode.getUserName()).orElse(null);
            Problems problem = problemsRepo.findById(runCode.getProblemId()).orElse(null);
            int points = 0;
            if (problem.getDifficulty().equals(ProblemDifficultyEnum.Easy)) {
                points = 10;
            } else if (problem.getDifficulty().equals(ProblemDifficultyEnum.Medium)) {
                points = 20;
            } else if (problem.getDifficulty().equals(ProblemDifficultyEnum.Hard)) {
                points = 30;
            }
            leaderBoard.setUserName(runCode.getUserName());
            leaderBoard.setTotalProblemsSolved(leaderBoardExisting.getTotalProblemsSolved() + 1);
            leaderBoard.setTotalPoints(leaderBoardExisting.getTotalPoints() + points);
            leaderBoardRepo.save(leaderBoard);
        } else {
            submitCode.setStatus(SubmissionStatusEnum.Rejected);
        }

        submissionsRepo.save(submitCode);
        return submissionsRepo.findById(submitCode.getSubmissionId()).orElse(null);
    }

    public List<SubmitCode> getSubmissionsByUserNameAndProblemId(String userName, int problemId) {
        return submissionsRepo.findByUserNameAndProblemId(userName, problemId);
    }

    public void deleteSubmissionBySubmissionId(String submissionId) {
        submissionsRepo.deleteById(submissionId);
    }

    public void deleteSubmissionsOfUser(String userName) {
        List<SubmitCode> submitCodeList = submissionsRepo.findByUserName(userName);
        for (SubmitCode submitCode : submitCodeList) {
            deleteSubmissionBySubmissionId(submitCode.getSubmissionId());
        }
    }
}
