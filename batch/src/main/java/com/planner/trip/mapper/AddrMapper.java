package com.planner.trip.mapper;

import com.planner.trip.model.Address;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class AddrMapper implements FieldSetMapper<Address> {

    @Override
    public Address mapFieldSet(FieldSet fieldSet) throws BindException {

        return Address.builder()
                .sido(fieldSet.readString(3))
                .sigungu(fieldSet.readString(4))
                .dong(fieldSet.readString(5))
                .build();
    }
}
