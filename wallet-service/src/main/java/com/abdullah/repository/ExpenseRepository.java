package com.abdullah.repository;

import com.abdullah.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    @Query("SELECT e FROM Expense e WHERE e.expenseAmount = (SELECT MAX(e2.expenseAmount) FROM Expense e2)")
    Expense findExpenseWithMaxAmount();

    @Query("SELECT e.category FROM Expense e WHERE e.expenseAmount = (SELECT MAX(e2.expenseAmount) FROM Expense e2)")
    String findCategoryWithMaxAmount();
    @Query("SELECT SUM(e.expenseAmount) FROM Expense e WHERE e.category = :givenCategory")
    Double findTotalExpenseByCategory(@Param("givenCategory") String givenCategory);
}
