package com.ebanx.api.services;

import com.ebanx.api.dtos.AccountDTO;
import com.ebanx.api.entities.Account;

public interface AccountService {

    public AccountDTO getAccountBalance(int id);

    public AccountDTO createAccount(Account account);
}
