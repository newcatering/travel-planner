package com.planner.trip.repository;

import com.planner.trip.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepo extends JpaRepository<Restaurant,Long> {
}
