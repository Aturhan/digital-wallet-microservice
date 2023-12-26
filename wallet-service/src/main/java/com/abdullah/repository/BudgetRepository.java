package com.abdullah.repository;

import com.abdullah.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BudgetRepository extends JpaRepository<Budget,Long> {
    Budget findBudgetByCategory(String category);
}
