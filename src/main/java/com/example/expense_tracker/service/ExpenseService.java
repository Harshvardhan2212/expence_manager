package com.example.expense_tracker.service;

import java.util.List;

import com.example.expense_tracker.dto.ExpenseDto;

public interface ExpenseService {

  ExpenseDto createExpense(ExpenseDto expenseDto);

  ExpenseDto getExpenseById(Long id);

  List<ExpenseDto> getAllExpenses();

  ExpenseDto updateExpense(Long id, ExpenseDto expenseDto);

  void deleteExpense(Long id);

}
