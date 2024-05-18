package org.parnassolutions.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.parnassolutions.DTOs.DepositDTO;
import org.parnassolutions.Entities.Deposit;
import org.parnassolutions.Repositories.DepositRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepositService {
    private final DepositRepository depositRepository;

    @NotNull
    @Transactional(readOnly = true)
    public Deposit findByDepositId(@NotNull Long depositId) {
        return depositRepository.findById(depositId)
                .orElseThrow(() -> new EntityNotFoundException("Operation not found"));
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
    public void deleteDepositById(@NotNull Long depositId) {
        depositRepository.deleteById(depositId);
    }

    @NotNull
    @Transactional
    public Deposit updateDepositById(@NotNull Long depositId, @NotNull DepositDTO dto) {
        Deposit deposit = depositRepository.findById(depositId)
                .orElseThrow(() -> new EntityNotFoundException("Operation not found"));
        if(dto.getDate() != null) deposit.setDate(dto.getDate());
        if(dto.getDescription() != null) deposit.setDescription(dto.getDescription());
        if(dto.getAmount() != null) deposit.setAmount(dto.getAmount());
        if(dto.getTitle() != null) deposit.setTitle(dto.getTitle());
        if(dto.getSource() != null) deposit.setSource(dto.getSource());

        return depositRepository.save(deposit);
    }

    @NotNull
    @Transactional
    public Deposit addDeposit(@NotNull DepositDTO dto) {
        return depositRepository.save(
                Deposit.builder()

                        .date(dto.getDate())
                        .description(dto.getDescription())
                        .amount(dto.getAmount())
                        .title(dto.getTitle())
                        .build()
        );
    }
}
