package org.parnassolutions.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.parnassolutions.DTOs.AccountDTO;
import org.parnassolutions.Entities.Account;
import org.parnassolutions.Repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    private final UserService userService;
    private final OperationService operationService;

    @NotNull
    @Transactional(readOnly = true)
    public Account findByAccountId(@NotNull Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account with id " + accountId + " not found"));
    }

    @NotNull
    @Transactional(readOnly = true)
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    /*@NotNull
    @Transactional(readOnly = true)
    public List<Account> extractAccounts(@NotNull UserDTO dto) {
        return dto.getAccounts()
                .stream()
                .map(accountRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }*/

    @NotNull
    @Transactional(readOnly = true)
    public Long getAccountsCount() {
        return accountRepository.count();
    }

    @NotNull
    @Transactional
    public Account addAccount(@NotNull AccountDTO dto) {
        return accountRepository.save(
                Account.builder()
                        .user(userService.findByUserId(dto.getUserId()))
                        .title(dto.getTitle())
                        .balance(dto.getBalance())
                        .currency(dto.getCurrency())
                        .operations(operationService.extractOperations(dto))
                        .build()
        );
    }

    @NotNull
    @Transactional
    public Account updateAccount(@NotNull Long accountId, @NotNull AccountDTO dto) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account with id " + accountId + " not found"));

        if(dto.getUserId() != null) account.setUser(userService.findByUserId(dto.getUserId()));
        if(dto.getTitle() != null) account.setTitle(dto.getTitle());
        if(dto.getBalance() != null) account.setBalance(dto.getBalance());
        if(dto.getCurrency() != null) account.setCurrency(dto.getCurrency());
        if(dto.getOperations() != null) account.setOperations(operationService.extractOperations(dto));

        return accountRepository.save(account);
    }

    @Transactional
    public void deleteAccount(@NotNull Long accountId) {
        accountRepository.deleteById(accountId);
    }
}
