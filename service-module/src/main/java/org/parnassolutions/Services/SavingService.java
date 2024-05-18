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

    @NotNull
    @Transactional(readOnly = true)
    public Saving findBySavingId(@NotNull Long savingId) {
        return savingRepository.findById(savingId)
                .orElseThrow(() -> new EntityNotFoundException("Operation not found"));
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

//    @NotNull
//    @Transactional
//    public Saving updateSavingById(@NotNull Long savingId, @NotNull SavingDTO dto) {
//        Saving saving = savingRepository.findById(savingId)
//                .orElseThrow(() -> new EntityNotFoundException("Operation not found"));
//        if(dto.getGoal() != null) saving.setGoal(dto.getGoal());//todo
//        if(dto.getDescription() != null) saving.setDescription(dto.getDescription());
//        if(dto.getAmount() != null) saving.setAmount(dto.getAmount());
//        if(dto.getTitle() != null) saving.setTitle(dto.getTitle());
//
//        return savingRepository.save(saving);
//    }

//    @NotNull
//    @Transactional
//    public Saving addSaving(@NotNull SavingDTO dto) {
//        return savingRepository.save(
//                Saving.builder()
//                        .date(dto.getDate())
//                        .goal()//todo
//                        .description(dto.getDescription())
//                        .amount(dto.getAmount())
//                        .title(dto.getTitle())
//                        .build()
//        );
//    }
}
