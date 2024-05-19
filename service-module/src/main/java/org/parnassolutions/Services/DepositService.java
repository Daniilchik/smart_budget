package org.parnassolutions.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.parnassolutions.DTOs.AccountDTO;
import org.parnassolutions.DTOs.DepositDTO;
import org.parnassolutions.Entities.Account;
import org.parnassolutions.Entities.Deposit;
import org.parnassolutions.Enums.OperationType;
import org.parnassolutions.Repositories.DepositRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepositService {
    private final DepositRepository depositRepository;
    private final AccountService accountService;

    @NotNull
    @Transactional(readOnly = true)
    public Deposit findByDepositId(@NotNull Long depositId) {
        return depositRepository.findById(depositId)
                .orElseThrow(() -> new EntityNotFoundException("Operation with id " + depositId + " not found."));
    }

    @NotNull
    @Transactional(readOnly = true)
    public List<Deposit> findAllDeposits() {
        return depositRepository.findAll();
    }

    @NotNull
    @Transactional(readOnly = true)
    public Long getDepositsCount() {
        return depositRepository.count();
    }

    @Transactional
    public void deleteDeposit(@NotNull Long depositId) {
        Deposit deposit = depositRepository.findById(depositId)
                .orElseThrow(() -> new EntityNotFoundException("Operation with id " + depositId + " not found."));
        Account account = deposit.getAccount();

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setBalance(account.getBalance() - deposit.getAmount());
        accountService.updateAccount(account.getAccountId(), accountDTO);

        depositRepository.deleteById(depositId);
    }

    @NotNull
    @Transactional
    public Deposit updateDeposit(@NotNull Long depositId, @NotNull DepositDTO dto) {
        Deposit deposit = depositRepository.findById(depositId)
                .orElseThrow(() -> new EntityNotFoundException("Operation with id " + depositId + " not found."));

        if (dto.getDate() != null) deposit.setDate(dto.getDate());
        if (dto.getDescription() != null) deposit.setDescription(dto.getDescription());
        if (dto.getAmount() != null) {
            Account account = deposit.getAccount();

            Double newAccountBalance = account.getBalance() - deposit.getAmount();
            deposit.setAmount(dto.getAmount());
            newAccountBalance+=deposit.getAmount();

            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setBalance(newAccountBalance);
            accountService.updateAccount(account.getAccountId(), accountDTO);
        }
        if (dto.getTitle() != null) deposit.setTitle(dto.getTitle());
        if (dto.getSource() != null) deposit.setSource(dto.getSource());

        return depositRepository.save(deposit);
    }

    @NotNull
    @Transactional
    public Deposit addDeposit(@NotNull DepositDTO dto) {
        Account account = accountService.findByAccountId(dto.getAccountId());

        Deposit deposit = Deposit.builder()
                .account(account)
                .source(dto.getSource())
                .date(dto.getDate())
                .description(dto.getDescription())
                .amount(dto.getAmount())
                .title(dto.getTitle())
                .operationType(OperationType.DEPOSIT)
                .build();

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setBalance(account.getBalance() + deposit.getAmount());
        accountService.updateAccount(account.getAccountId(), accountDTO);

        return depositRepository.save(deposit);
    }
}
