package com.example.app.batch;

import com.example.app.model.Employee;
import com.example.app.model.EmployeeDetail;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class EmployeeBatchConfig {
    public static final int CHUNK_SIZE = 2;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    PlatformTransactionManager batchTransactionManager;

    @Autowired
    private EmployeeReader employeeReader;
    @Autowired
    private EmployeeProcessor employeeProcessor;
    @Autowired
    private EmployeeDetailLogWriter employeeWriter;

    @Bean(name = "employeeDataJob")
    public Job employeeDataJob(@Autowired @Qualifier("employeeReaderStep") Step studentStep ) {
        return new JobBuilder("Employee Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(studentStep)
                .end().build();
    }

    @Bean("employeeReaderStep")
    public Step employeeReaderStep(@Autowired @Qualifier("employeeItemReader") ItemReader<Employee> reader) {
        return new StepBuilder("EmployeeReaderStep", jobRepository)
                .<Employee, EmployeeDetail>chunk(CHUNK_SIZE, batchTransactionManager)
                .reader(reader)
                .processor(employeeProcessor)
                .writer(employeeWriter)
                .build();
    }

    @StepScope
    @Bean("employeeItemReader")
    public JdbcPagingItemReader<Employee> employeeItemReader() {
        return employeeReader.getPaginationReader();
    }
}

