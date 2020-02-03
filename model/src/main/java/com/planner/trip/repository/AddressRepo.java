package com.planner.trip.repository;


import com.planner.trip.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Long> {
    Boolean existsByDong(String dong);
}
