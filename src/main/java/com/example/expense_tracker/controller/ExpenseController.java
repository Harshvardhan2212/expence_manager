package com.example.expense_tracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.example.expense_tracker.dto.ExpenseDto;
import com.example.expense_tracker.service.ExpenseService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/expense")
@AllArgsConstructor
public class ExpenseController {
  private final ExpenseService expenseService;

  @PostMapping
  public ResponseEntity<ExpenseDto> create(@RequestBody ExpenseDto expenseDto) {
    ExpenseDto savedExpenseDto = expenseService.createExpense(expenseDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedExpenseDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ExpenseDto> getById(@PathVariable Long id) {
    ExpenseDto expenseDto = expenseService.getExpenseById(id);
    return ResponseEntity.ok(expenseDto);
  }

  @GetMapping
  public ResponseEntity<List<ExpenseDto>> getAll() {
    List<ExpenseDto> expenses = expenseService.getAllExpenses();
    return ResponseEntity.ok(expenses);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ExpenseDto> update(@PathVariable Long id, @RequestBody ExpenseDto expenseDto) {
    ExpenseDto updatedExpense = expenseService.updateExpense(id, expenseDto);
    return ResponseEntity.ok(updatedExpense);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {

    expenseService.deleteExpense(id);

    return ResponseEntity.ok(Map.of("message", "Expense Deleted Successfully"));
  }

}
