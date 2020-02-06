package com.planner.trip.repository;


import com.planner.trip.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AddressRepo extends JpaRepository<Address,Long> {
    Boolean existsByDong(String dong);
    @Query(value = "SELECT idx FROM ADDRESS ORDER BY idx DESC LiMIT 1",nativeQuery = true)
    Long findLastIdx();
    Optional<Address> findByIdx(Long idx);
}
