package com.example.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

@EnableAsync
@RestController
@RequestMapping(path = "/v1")
public class BatchController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job jobSendMessage;

    @Async
    @GetMapping("/run-batch")
    public CompletableFuture<ResponseEntity<String>> runBatch() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                jobLauncher.run(jobSendMessage, new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters());
                return ResponseEntity.ok("Batch job has been invoked");
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body("Batch job failed to start");
            }
        });
    }
}
