package com.ebanx.api.mappers;

import com.ebanx.api.dtos.AccountDTO;
import com.ebanx.api.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {

    AccountDTO toDto(Account account);

}