package com.ebanx.api.services;

import com.ebanx.api.dtos.RequestDTO;
import org.apache.coyote.BadRequestException;

public interface AccountService {

    Object event(RequestDTO requestDTO) throws BadRequestException;

    Integer getAccountBalance(Long account_id);

    void resetDb();

}
