package com.ebanx.api.controller;

import com.ebanx.api.dtos.RequestDTO;
import com.ebanx.api.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/event")
    public ResponseEntity<Object> event(@RequestBody RequestDTO requestDTO) throws BadRequestException {
        Object response = accountService.event(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/balance")
    public ResponseEntity<Object> event(@RequestParam Long account_id) {
        return ResponseEntity.ok(accountService.getAccountBalance(account_id));
    }
}
