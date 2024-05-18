package org.parnassolutions.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.parnassolutions.DTOs.ExpenseDTO;
import org.parnassolutions.Entities.Expense;
import org.parnassolutions.Repositories.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    @NotNull
    @Transactional(readOnly = true)
    public Expense findByExpenseId(@NotNull Long expenseId) {
        return expenseRepository.findById(expenseId)
                .orElseThrow(() -> new EntityNotFoundException("Operation not found"));
    }

    @NotNull
    @Transactional(readOnly = true)
    public List<Expense> findAllExpenses() {
        return expenseRepository.findAll();
    }

    @NotNull
    @Transactional(readOnly = true)
    public Long getExpensesCount() {
        return expenseRepository.count();
    }

    @Transactional
    public void deleteExpenseById(@NotNull Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }

    @NotNull
    @Transactional
    public Expense updateExpenseById(@NotNull Long expenseId, @NotNull ExpenseDTO dto) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new EntityNotFoundException("Operation not found"));
        if(dto.getDate() != null) expense.setDate(dto.getDate());
        if(dto.getCategory() != null) expense.setCategory(dto.getCategory());
        if(dto.getDescription() != null) expense.setDescription(dto.getDescription());
        if(dto.getAmount() != null) expense.setAmount(dto.getAmount());
        if(dto.getTitle() != null) expense.setTitle(dto.getTitle());

        return expenseRepository.save(expense);
    }

    @NotNull
    @Transactional
    public Expense addExpense(@NotNull ExpenseDTO dto) {
        return expenseRepository.save(
                Expense.builder()
                        .category(dto.getCategory())
                        .date(dto.getDate())
                        .description(dto.getDescription())
                        .amount(dto.getAmount())
                        .title(dto.getTitle())
                        .build()
        );
    }
}
