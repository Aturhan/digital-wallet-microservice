package com.abdullah.service;

import com.abdullah.model.Budget;
import com.abdullah.model.Expense;
import com.abdullah.repository.BudgetRepository;
import com.abdullah.repository.ExpenseRepository;
import org.springframework.stereotype.Service;



@Service
public class AnalyzeService {
    private final ExpenseRepository expenseRepository;
    private final BudgetRepository budgetRepository;

    public AnalyzeService(ExpenseRepository expenseRepository, BudgetRepository budgetRepository) {
        this.expenseRepository = expenseRepository;
        this.budgetRepository = budgetRepository;
    }

    public String getAnalyze(String category){
       Expense theBiggestExpenditure = expenseRepository.findExpenseWithMaxAmount();

       String theBiggestExpenseForCategory = expenseRepository.findCategoryWithMaxAmount();

       Double totalExpenseByCategory = expenseRepository.findTotalExpenseByCategory(category);

       Budget budget = budgetRepository.findBudgetByCategory(category);


        return String.format("The biggest expenditure = %s%n",(theBiggestExpenditure != null) ? theBiggestExpenditure.getExpenseAmount().toString() : "No data found!" ) +
        String.format("The biggest expense for category = %s%n", (theBiggestExpenseForCategory != null) ? theBiggestExpenseForCategory : "No data found!") +
                String.format("The total expense for category = %s%n",(totalExpenseByCategory != null ) ? totalExpenseByCategory.toString() : "No data found!");
    }


}
