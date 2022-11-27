package com.github.joeri5.jobboard.job;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
@AllArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Job createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @GetMapping("{id}")
    public Job readJob(@PathVariable Long id) {
        return jobService.readJob(id);
    }

    @GetMapping
    public List<Job> readJobs() {
        return jobService.readJobs();
    }


    @PutMapping("{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job job) {
        return jobService.updateJob(id, job);
    }

    @DeleteMapping("{id}")
    public void deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
    }

}
