package org.parnassolutions.Controllers;

import lombok.RequiredArgsConstructor;
import org.parnassolutions.DTOs.AccountDTO;
import org.parnassolutions.Entities.Account;
import org.parnassolutions.Services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping(value = "/{accountId}", produces = APPLICATION_JSON_VALUE)
    public Account findByAccountId(@PathVariable("accountId") Long accountId) {
        return accountService.findByAccountId(accountId);
    }

    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public List<Account> findAllAccounts() {
        return accountService.findAllAccounts();
    }

    @GetMapping(value = "count")
    public Long getAccountsCount() {
        return accountService.getAccountsCount();
    }

    @PostMapping(value = "/add", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Account addAccount(@RequestBody AccountDTO dto) {
        return accountService.addAccount(dto);
    }

    @PatchMapping(value = "/update/{accountId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Account updateAccount(@PathVariable("accountId") Long accountId, @RequestBody AccountDTO dto) {
        return accountService.updateAccount(accountId, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/delete/{accountId}", produces = APPLICATION_JSON_VALUE)
    public void deleteAccount(@PathVariable("accountId") Long accountId) {
        accountService.deleteAccount(accountId);
    }
}
