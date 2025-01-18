package com.example.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1")
public class BatchController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job jobSendMessage;

    @GetMapping("/run-batch")
    public String runBatch() {
        try {
            jobLauncher.run(jobSendMessage, new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters());
            return "Batch job has been invoked";
        } catch (Exception e) {
            e.printStackTrace();
            return "Batch job failed to start";
        }
    }
}
