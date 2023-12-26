package com.abdullah.controller;

import com.abdullah.dto.AddAmountRequest;
import com.abdullah.dto.WalletDto;
import com.abdullah.service.AnalyzeService;
import com.abdullah.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/wallet")
public class WalletController {
    private final WalletService walletService;
    private final AnalyzeService analyzeService;

    public WalletController(WalletService walletService, AnalyzeService analyzeService) {
        this.walletService = walletService;
        this.analyzeService = analyzeService;
    }
    @GetMapping(path = "/get")
    public ResponseEntity<WalletDto> getWallet(@RequestParam ("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(walletService.getWallet(id));
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addAmount(@RequestBody AddAmountRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(walletService.addAmount(request));
    }
    @GetMapping(path = "/analyze")
    public ResponseEntity<String> getAnalyze(@RequestParam ("category") String category){
        return ResponseEntity.status(HttpStatus.OK).body(analyzeService.getAnalyze(category));
    }
}
