package com.Javacode.test.service;

import com.Javacode.test.WalletExceptions.InsufficientFundsException;
import com.Javacode.test.WalletExceptions.WalletNotFoundException;
import com.Javacode.test.dto.WalletDto;
import com.Javacode.test.dto.WalletRequest;
import com.Javacode.test.model.Wallet;
import com.Javacode.test.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService{

    private WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public void processWalletOperation(WalletRequest walletRequest) {
        Wallet wallet = walletRepository.findById(walletRequest.getValletId())
                .orElseThrow(() -> new WalletNotFoundException(walletRequest.getValletId()));

        BigDecimal newBalance;
        if (walletRequest.getOperationType() == WalletRequest.OperationType.DEPOSIT) {
            newBalance = wallet.getBalance().add(walletRequest.getAmount());
        } else {
            if (wallet.getBalance().compareTo(walletRequest.getAmount()) < 0) {
                throw new InsufficientFundsException();
            }
            newBalance = wallet.getBalance().subtract(walletRequest.getAmount());
        }

        wallet.setBalance(newBalance);
        walletRepository.save(wallet);
    }

    public WalletDto getWalletBalance(UUID walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException(walletId));

        return new WalletDto(wallet.getId(), wallet.getBalance());
    }
}
