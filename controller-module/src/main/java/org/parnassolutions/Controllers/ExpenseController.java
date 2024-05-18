package org.parnassolutions.Controllers;

import lombok.RequiredArgsConstructor;
import org.parnassolutions.DTOs.ExpenseDTO;
import org.parnassolutions.Entities.Expense;
import org.parnassolutions.Services.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @GetMapping(value = "/{expenseId}", produces = APPLICATION_JSON_VALUE)
    public Expense findByExpenseId(@PathVariable("expenseId") Long expenseId) {
        return expenseService.findByExpenseId(expenseId);
    }

    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public List<Expense> findAllExpenses() {
        return expenseService.findAllExpenses();
    }

    @GetMapping(value = "/count")
    public Long getExpensesCount() {
        return expenseService.getExpensesCount();
    }

    @PostMapping(value = "/add", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Expense addExpense(@RequestBody ExpenseDTO dto) {
        return expenseService.addExpense(dto);
    }

    @PostMapping(value = "/update/{expenseId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Expense updateExpense(@PathVariable("expenseId") Long expenseId, @RequestBody ExpenseDTO dto) {
        return expenseService.updateExpenseById(expenseId, dto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/delete/{expenseId}", produces = APPLICATION_JSON_VALUE)
    public void deleteExpense(@PathVariable("expenseId") Long expenseId) {
        expenseService.deleteExpenseById(expenseId);
    }
}
