package org.parnassolutions.Controllers;

import lombok.RequiredArgsConstructor;
import org.parnassolutions.DTOs.AccountDTO;
import org.parnassolutions.Entities.Account;
import org.parnassolutions.Services.AccountService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PreAuthorize("hasAuthority('ADMIN') or @accountService.isOwner(#accountId, principal.username)")
    @GetMapping(value = "/{accountId}", produces = APPLICATION_JSON_VALUE)
    public Account findByAccountId(@Param("accountId") @PathVariable("accountId") Long accountId) {
        return accountService.findByAccountId(accountId);
    }

    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    @PostFilter("hasAuthority('ADMIN') or filterObject.user.email == principal.username")
    public List<Account> findAllAccounts() {
        return accountService.findAllAccounts();
    }

    @GetMapping(value = "count")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Long getAccountsCount() {
        return accountService.getAccountsCount();
    }

    @PostMapping(value = "/add", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN') or #dto.userId == @userService.findByEmail(principal.username).userId")
    public Account addAccount(@Param("dto") @RequestBody AccountDTO dto) {
        return accountService.addAccount(dto);
    }

    @PatchMapping(value = "/update/{accountId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN') or @accountService.isOwner(#accountId, principal.username)")
    public Account updateAccount(@Param("accountId") @PathVariable("accountId") Long accountId, @RequestBody AccountDTO dto) {
        return accountService.updateAccount(accountId, dto);
    }

    @PreAuthorize("hasAuthority('ADMIN') or @accountService.isOwner(#accountId, principal.username)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/delete/{accountId}", produces = APPLICATION_JSON_VALUE)
    public void deleteAccount(@Param("accountId") @PathVariable("accountId") Long accountId) {
        accountService.deleteAccount(accountId);
    }
}
