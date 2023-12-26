package com.abdullah.service;

import com.abdullah.dto.ExpenseDto;
import com.abdullah.dto.ExpenseRequest;
import com.abdullah.exception.MyEntityNotFoundException;
import com.abdullah.exception.NotEnoughAmountToExpenseException;
import com.abdullah.model.Expense;
import com.abdullah.model.Wallet;
import com.abdullah.repository.ExpenseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final WalletService walletService;

    public ExpenseService(ExpenseRepository expenseRepository, WalletService walletService) {
        this.expenseRepository = expenseRepository;
        this.walletService = walletService;
    }

    public ExpenseDto getExpense(Long id){
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException("Expense not found"));
        return ExpenseDto.builder()
                .id(expense.getId())
                .category(expense.getCategory())
                .description(expense.getDescription())
                .fullName(expense.getWallet().getFullName())
                .walletId(expense.getWallet().getId())
                .build();
    }

    public String addExpense(ExpenseRequest request) throws NotEnoughAmountToExpenseException {
        Wallet wallet = walletService.findWallet(request.walletId());

        if(wallet.getAmount() < request.expenseAmount()){
            throw new NotEnoughAmountToExpenseException("Not enough amount to expense");
        }
       final Double newAmount = wallet.getAmount() - request.expenseAmount();
        wallet.setAmount(newAmount);
        walletService.updateWallet(wallet);

        Expense expense = Expense.builder()
                .expenseAmount(request.expenseAmount())
                .description(request.description())
                .category(request.category())
                .wallet(wallet)
                .build();
       Expense ex =  expenseRepository.save(expense);
        return "Expense added";

    }
}
