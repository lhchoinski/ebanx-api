package com.ebanx.api.services.impl;

import com.ebanx.api.db.DbAccount;
import com.ebanx.api.dtos.*;
import com.ebanx.api.entities.Account;
import com.ebanx.api.mappers.AccountMapper;
import com.ebanx.api.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final DbAccount dbAccount;
    private final AccountMapper accountMapper;


    @Override
    public Object event(RequestDTO requestDTO) throws BadRequestException {

        switch (requestDTO.getType()) {
            case deposit -> {
                return deposit(requestDTO);
            }
            case withdraw -> {
                return withdraw(requestDTO);
            }
            case transfer -> {
                return transfer(requestDTO);
            }

            default -> throw new BadRequestException("Invalid request type");
        }
    }

    @Override
    public Integer getAccountBalance(Long account_id) {
        Account account = getAccount(account_id);

        if (account == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        AccountDTO accountDTO = accountMapper.toDto(account);
        return accountDTO.getBalance();
    }

    @Override
    public void resetDb() {
        dbAccount.deleteAll();
    }

    private TransferDTO transfer(RequestDTO requestDTO) {
        Account originAccount = getAccount(requestDTO.getOrigin());
        Account destinationAccount = getAccount(requestDTO.getDestination());

        if (originAccount == null || destinationAccount == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        originAccount.setBalance(originAccount.getBalance() - requestDTO.getAmount());
        destinationAccount.setBalance(destinationAccount.getBalance() + requestDTO.getAmount());

        dbAccount.save(originAccount);
        dbAccount.save(destinationAccount);

        AccountDTO originAccountDTO = accountMapper.toDto(originAccount);
        AccountDTO destinationAccountDTO = accountMapper.toDto(destinationAccount);

        return new TransferDTO(originAccountDTO, destinationAccountDTO);

    }

    private WithdrawDTO withdraw(RequestDTO requestDTO) {
        Account account = getAccount(requestDTO.getOrigin());

        if (account == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        account.setBalance(account.getBalance() - requestDTO.getAmount());

        dbAccount.save(account);

        AccountDTO accountDTO = accountMapper.toDto(account);

        return new WithdrawDTO(accountDTO);
    }

    private DepositDTO deposit(RequestDTO requestDTO) {
        Account account = getAccount(requestDTO.getDestination());

        if (account == null) {
            account = new Account();
            account.setId(requestDTO.getDestination());
            account.setBalance(requestDTO.getAmount());
        } else {
            Integer novoSaldo = account.getBalance() + requestDTO.getAmount();
            account.setBalance(novoSaldo);
        }

        dbAccount.save(account);

        AccountDTO accountDTO = accountMapper.toDto(account);
        return new DepositDTO(accountDTO);
    }

    private Account getAccount(Long id) {
        return dbAccount.findById(id).orElse(null);
    }

}
