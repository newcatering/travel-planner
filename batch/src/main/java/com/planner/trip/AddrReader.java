package com.planner.trip;

import com.planner.trip.mapper.AddrMapper;
import com.planner.trip.model.Address;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

public class AddrReader implements ItemReader<Address> {

    @Override
    public Address read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        FlatFileItemReader<Address> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("C:\\dev\\sigungu_data\\entrc_busan.txt"));
        DefaultLineMapper<Address> lineMapper = new DefaultLineMapper<>();
//DelimitedLineTokenizer defaults to comma as its delimiter
        lineMapper.setLineTokenizer(new DelimitedLineTokenizer("|"));
        lineMapper.setFieldSetMapper(new AddrMapper());
        itemReader.setLineMapper(lineMapper);
        itemReader.open(new ExecutionContext());
        Address addr = itemReader.read();

        return addr;
    }
}
