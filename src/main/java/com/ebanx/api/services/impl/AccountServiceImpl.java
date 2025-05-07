package com.ebanx.api.services.impl;

import com.ebanx.api.dtos.AccountDTO;
import com.ebanx.api.entities.Account;
import com.ebanx.api.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Override
    public AccountDTO getAccountBalance(int id) {
        return null;
    }

    @Override
    public AccountDTO createAccount(Account account) {
        return null;
    }
}
