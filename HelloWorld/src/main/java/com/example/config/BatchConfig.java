package com.example.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.steps.processor.UserItemProcessor;
import com.example.steps.reader.UserItemReader;
import com.example.steps.writer.UserItemWriter;

@Configuration
@EnableBatchProcessing
//@EnableTransactionManagement
public class BatchConfig {


	@Autowired
	UserRepository userRepository;
	
	 @Autowired
     private DataSource dataSource;

     @Bean
     //@Primary
     public JpaTransactionManager transactionManager() {
          final JpaTransactionManager tm = new JpaTransactionManager();
          tm.setDataSource(dataSource);
          return tm;
     }
     
//   @Bean
//   public PlatformTransactionManager transactionManager(DataSource dataSource) {
//	   return new DataSourceTransactionManager(dataSource);
//   }

    @Bean
    public Job jobSendMessage(JobRepository jobRepository, Step step) {
        return new JobBuilder("jobSendMessage", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, JpaTransactionManager transactionManager) {
        return new StepBuilder("step", jobRepository)
                .<User, User>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

    
    @Bean
    @StepScope
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
