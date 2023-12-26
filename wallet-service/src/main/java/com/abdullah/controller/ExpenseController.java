package com.abdullah.controller;

import com.abdullah.dto.ExpenseDto;
import com.abdullah.dto.ExpenseRequest;
import com.abdullah.exception.NotEnoughAmountToExpenseException;
import com.abdullah.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
    @GetMapping(path = "/get")
    public ResponseEntity<ExpenseDto> getExpense(@RequestParam ("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(expenseService.getExpense(id));
    }
    @PostMapping(path = "/add")
    public ResponseEntity<String> addExpense(@RequestBody ExpenseRequest request) throws NotEnoughAmountToExpenseException {
        return ResponseEntity.status(HttpStatus.OK).body(expenseService.addExpense(request));
    }
}
