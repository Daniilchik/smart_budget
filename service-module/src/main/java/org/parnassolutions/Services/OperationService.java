package org.parnassolutions.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.parnassolutions.DTOs.AccountDTO;
import org.parnassolutions.Entities.Operation;
import org.parnassolutions.Repositories.OperationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final OperationRepository operationRepository;

    @NotNull
    @Transactional(readOnly = true)
    public Operation findByOperationId(@NotNull Long operationId) {
        return operationRepository.findById(operationId)
                .orElseThrow(() -> new EntityNotFoundException("Operation with id " + operationId + " not found."));
    }

    @NotNull
    @Transactional(readOnly = true)
    public List<Operation> findAllOperations() {
        return operationRepository.findAll();
    }

    @NotNull
    @Transactional(readOnly = true)
    public Long getOperationsCount() {
        return operationRepository.count();
    }

    @NotNull
    @Transactional(readOnly = true)
    public List<Operation> extractOperations(@NotNull AccountDTO dto) {
        return dto.getOperations()
                .stream()
                .map(operationRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
