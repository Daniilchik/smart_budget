package org.parnassolutions.Services;

import lombok.AllArgsConstructor;
import org.parnassolutions.DTOs.AccountDTO;
import org.jetbrains.annotations.NotNull;
import org.parnassolutions.Entities.Account;
import org.parnassolutions.Repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @NotNull
    @Transactional
    public Account createAccount(@NotNull AccountDTO dto) {
        return accountRepository.save(
                Account.builder()
                        .budget(dto.getBudget())
                        .income(dto.getIncome())
                        .expense(dto.getExpense())
                        .build()
        );
    }

    @Transactional
    public void deleteAccount(@NotNull Integer id) {
        accountRepository.deleteById(id);
    }

    @Transactional
    public void setBudget(@NotNull Integer id, @NotNull Long budget) throws AccountNotFoundException {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(" "));
        account.setBudget(budget);
    }

    @Transactional
    public void setIncome(@NotNull Integer id, @NotNull Long income) throws AccountNotFoundException {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(" "));
        account.setIncome(account.getIncome() + income);

    }

    @Transactional
    public void setExpense(@NotNull Integer id, @NotNull Long expense) throws AccountNotFoundException {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(" "));
        account.setExpense(account.getExpense() + expense);
    }
}
