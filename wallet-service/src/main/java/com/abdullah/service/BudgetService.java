package com.abdullah.service;

import com.abdullah.dto.AddBudgetRequest;
import com.abdullah.dto.BudgetDto;
import com.abdullah.exception.MyEntityNotFoundException;
import com.abdullah.model.Budget;
import com.abdullah.model.Wallet;
import com.abdullah.repository.BudgetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final WalletService walletService;

    public BudgetService(BudgetRepository budgetRepository, WalletService walletService) {
        this.budgetRepository = budgetRepository;
        this.walletService = walletService;
    }

    public BudgetDto getBudget(String category){
       Budget budget = budgetRepository.findBudgetByCategory(category);

       if (budget != null){
           return BudgetDto.builder()
                   .fullName(budget.getWallet().getFullName())
                   .budgetAmount(budget.getBudgetAmount())
                   .description(budget.getDescription())
                   .category(budget.getCategory())
                   .build();
       }
       throw new MyEntityNotFoundException("Budget Not Found");
    }

    public void addBudget(AddBudgetRequest request){
        Wallet wallet = walletService.findWallet(request.walletId());
        Budget budget = Budget.builder()
                .category(request.category())
                .budgetAmount(request.budgetAmount())
                .description(request.description())
                .wallet(wallet)
                .build();
    }
}
