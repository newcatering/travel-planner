package com.planner.trip.task;

import com.planner.trip.mapper.AddrMapper;
import com.planner.trip.model.Address;
import com.planner.trip.repository.AddressRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
@Slf4j
public class AddrTasklet implements Tasklet {
    @Autowired
    AddressRepo addressRepo;
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        FlatFileItemReader<Address> itemReader = new FlatFileItemReader<>();
        itemReader.setEncoding("UTF-8");
        itemReader.setResource(new FileSystemResource("C:\\dev\\sigungu_data\\entrc_chungbuk.txt"));
        DefaultLineMapper<Address> lineMapper = new DefaultLineMapper<>();
        //DelimitedLineTokenizer defaults to comma as its delimiter
        lineMapper.setLineTokenizer(new DelimitedLineTokenizer("|"));
        lineMapper.setFieldSetMapper(new AddrMapper());
        itemReader.setLineMapper(lineMapper);
        itemReader.open(new ExecutionContext());

        while(true){
            Address addr = itemReader.read();
            if(addr == null){
                break;
            }
            log.info(addr.toString());
            if(addressRepo.existsByDong(addr.getDong()) == true){
                continue;
            }
            addressRepo.save(addr);
        }

        return RepeatStatus.FINISHED;
    }
}
