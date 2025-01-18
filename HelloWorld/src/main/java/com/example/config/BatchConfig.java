package com.example.config;

import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.steps.processor.UserItemProcessor;
import com.example.steps.writer.UserItemWriter;
import com.example.steps.reader.UserItemReader;

@Configuration
@EnableBatchProcessing
public class BatchConfig {


	@Autowired
	UserRepository userRepository;
	
   @Bean
   public PlatformTransactionManager transactionManager(DataSource dataSource) {
	   return new DataSourceTransactionManager(dataSource);
   }

    @Bean
    public Job jobSendMessage(JobRepository jobRepository, Step step) {
        return new JobBuilder("jobSendMessage", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, DataSourceTransactionManager transactionManager) {
        return new StepBuilder("step", jobRepository)
                .<User, User>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

    
    @Bean
    public ItemReader<User> reader() {
        return new UserItemReader(userRepository);
    }

    @Bean
    public ItemProcessor<User, User> processor() {
        return new UserItemProcessor();
    }

    @Bean
    public ItemWriter<User> writer() {
        return new UserItemWriter();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor("spring_batch");
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }
}
