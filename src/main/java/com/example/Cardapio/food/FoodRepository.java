package com.example.Cardapio.food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
