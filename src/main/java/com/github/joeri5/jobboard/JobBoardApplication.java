package com.github.joeri5.jobboard;

import com.github.joeri5.jobboard.job.Job;
import com.github.joeri5.jobboard.job.JobRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JobBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobBoardApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(JobRepository jobRepository) {
		return (args) -> {
			Job javaJob = new Job(
					"Looking for a Java Developer",
					"Java Developer with 2+ years of experience"
			);
			Job pythonJob = new Job(
					"Looking for a Python Developer",
					"Python Developer with 2+ years of experience"
			);

			jobRepository.saveAll(List.of(javaJob, pythonJob));
		};
	}

}
