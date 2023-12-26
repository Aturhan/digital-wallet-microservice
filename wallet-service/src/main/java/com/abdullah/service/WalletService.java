package com.abdullah.service;


import com.abdullah.dto.AddAmountRequest;
import com.abdullah.dto.UserKafkaPayload;
import com.abdullah.dto.WalletDto;
import com.abdullah.exception.MyEntityNotFoundException;

import com.abdullah.model.Wallet;
import com.abdullah.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public WalletDto getWallet(Long id){
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new MyEntityNotFoundException("Wallet not found"));
        return WalletDto.builder()
                .id(wallet.getId())
                .amount(wallet.getAmount())
                .fullName(wallet.getFullName())
                .emailAddress(wallet.getEmailAddress())
                .build();
    }


    public void createWallet(UserKafkaPayload payload){
        Long id = ThreadLocalRandom.current().nextLong(1_000_000, 10_000_000);
        final Wallet wallet = Wallet.builder()
                .id(id)
                .amount(0.00)
                .userId(payload.userId())
                .fullName(payload.fullName())
                .emailAddress(payload.email())
                .location(payload.location())
                .build();
       Wallet saved = walletRepository.save(wallet);
        log.info(String.format("Wallet created = %s",saved));
    }
    @Transactional
    public String addAmount(AddAmountRequest request){
        Wallet wallet = walletRepository.findById(request.walletId())
                .orElseThrow(() -> new MyEntityNotFoundException("Wallet not found"));
      final Double newAmount = wallet.getAmount() + request.newAmount();
      wallet.setAmount(newAmount);
       final Wallet w = walletRepository.save(wallet);
        return String.format("Amount = %f",w.getAmount());
    }

    protected Wallet findWallet(Long walletId){
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new MyEntityNotFoundException("Wallet not found"));

    }
    @Transactional
    protected void updateWallet(Wallet wallet){
        walletRepository.save(wallet);
    }


}
