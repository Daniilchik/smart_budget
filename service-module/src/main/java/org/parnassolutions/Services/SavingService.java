package org.parnassolutions.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.parnassolutions.DTOs.SavingDTO;
import org.parnassolutions.Entities.Saving;
import org.parnassolutions.Repositories.SavingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SavingService {
    private final SavingRepository savingRepository;
    private final GoalService goalService;
    private final AccountService accountService;

    @NotNull
    @Transactional(readOnly = true)
    public Saving findBySavingId(@NotNull Long savingId) {
        return savingRepository.findById(savingId)
                .orElseThrow(() -> new EntityNotFoundException("Operation with id " + savingId + " not found."));
    }

    @NotNull
    @Transactional(readOnly = true)
    public List<Saving> findAllSavings() {
        return savingRepository.findAll();
    }

    @NotNull
    @Transactional(readOnly = true)
    public Long getSavingsCount() {
        return savingRepository.count();
    }

    @Transactional
    public void deleteSavingById(@NotNull Long savingId) {
        savingRepository.deleteById(savingId);
    }

    @NotNull
    @Transactional
    public Saving updateSavingById(@NotNull Long savingId, @NotNull SavingDTO dto) {
        Saving saving = savingRepository.findById(savingId)
                .orElseThrow(() -> new EntityNotFoundException("Operation with id " + savingId + " not found."));

        if(dto.getAccountId() != null) saving.setAccount(accountService.findByAccountId(dto.getAccountId()));
        if(dto.getGoalId() != null) saving.setGoal(goalService.findByGoalId(dto.getGoalId()));
        if(dto.getDescription() != null) saving.setDescription(dto.getDescription());
        if(dto.getDate() != null) saving.setDate(dto.getDate());
        if(dto.getAmount() != null) saving.setAmount(dto.getAmount());
        if(dto.getTitle() != null) saving.setTitle(dto.getTitle());
        if(dto.getOperationType() != null) saving.setOperationType(dto.getOperationType());

        return savingRepository.save(saving);
    }

    @NotNull
    @Transactional
    public Saving addSaving(@NotNull SavingDTO dto) {
        return savingRepository.save(
                Saving.builder()
                        .account(accountService.findByAccountId(dto.getAccountId()))
                        .date(dto.getDate())
                        .goal(goalService.findByGoalId(dto.getGoalId()))
                        .description(dto.getDescription())
                        .amount(dto.getAmount())
                        .title(dto.getTitle())
                        .operationType(dto.getOperationType())
                        .build()
        );
    }
}
