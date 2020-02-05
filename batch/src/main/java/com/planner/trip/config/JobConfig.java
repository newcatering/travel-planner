package com.planner.trip.config;

import com.planner.trip.AddrReader;
import com.planner.trip.mapper.AddrMapper;
import com.planner.trip.model.Address;
import com.planner.trip.task.AddrTasklet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Slf4j
@Configuration
public class JobConfig {
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    public JobConfig(JobBuilderFactory jobBuilderFactory,
                     StepBuilderFactory stepBuilderFactory){
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job testJob() {
        return jobBuilderFactory.get("entrc_gangwon")
                .start(simpleStep1())
                .build();
    }

    @Bean
    public Step simpleStep1() {
        return stepBuilderFactory.get("testStep1")
                //.<Address, Address>chunk(2)
                //.reader(flatFileItemReader())
                //.writer(jpaItemWriter())
                .tasklet(addrTasklet())

                .build();

    }
    @Bean
    public AddrTasklet addrTasklet(){
        return new AddrTasklet();
    }

//    @Bean
//    public FlatFileItemReader flatFileItemReader(){
//        FlatFileItemReader<Address> itemReader = new FlatFileItemReader<>();
//        itemReader.setEncoding("UTF-8");
//        itemReader.setResource(new FileSystemResource("C:\\dev\\sigungu_data\\entrc_busan.txt"));
//        DefaultLineMapper<Address> lineMapper = new DefaultLineMapper<>();
//        //DelimitedLineTokenizer defaults to comma as its delimiter
//        lineMapper.setLineTokenizer(new DelimitedLineTokenizer("|"));
//        lineMapper.setFieldSetMapper(new AddrMapper());
//        itemReader.setLineMapper(lineMapper);
//        itemReader.open(new ExecutionContext());
//
//        return itemReader;
//    }
//
//    @Bean
//    public ItemWriter<Address> jpaItemWriter(){
//
//
//        return list -> {
//            for(Address address : list){
//                System.out.println(address.toString());
//            }
//        };
//    }
}
