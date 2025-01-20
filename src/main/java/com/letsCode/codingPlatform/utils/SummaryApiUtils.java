package com.letsCode.codingPlatform.utils;

import org.springframework.stereotype.Component;

@Component
public class SummaryApiUtils {
    
    public long startFunction() {
        long startTime = System.currentTimeMillis();
        return startTime;
    }

    public double endFunction(long startTime) {
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        // return time taken in seconds
        return executionTime / 1000.0;
    }
}
