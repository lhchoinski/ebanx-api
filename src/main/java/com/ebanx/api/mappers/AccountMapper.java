package com.ebanx.api.mappers;

import com.ebanx.api.dtos.AccountDTO;
import com.ebanx.api.entities.Account;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {
    Account toEntity(AccountDTO accountDTO);

    AccountDTO toDto(Account account);

    List<AccountDTO> toDto(List<Account> accounts);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Account partialUpdate(AccountDTO accountDTO, @MappingTarget Account account);
}