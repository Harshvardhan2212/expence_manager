package com.example.expense_tracker.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.expense_tracker.dto.CategoryDto;
import com.example.expense_tracker.dto.ExpenseDto;
import com.example.expense_tracker.entity.Expense;
import com.example.expense_tracker.exception.ResourseNotFoundException;
import com.example.expense_tracker.mapper.CategoryMapper;
import com.example.expense_tracker.mapper.ExpenseMapper;
import com.example.expense_tracker.repository.ExpenseRepository;
import com.example.expense_tracker.service.CategoryService;
import com.example.expense_tracker.service.ExpenseService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {

  private final ExpenseRepository expenseRepository;

  private final CategoryService categoryService;

  @Override
  public ExpenseDto createExpense(ExpenseDto expenseDto) {
    Expense expense = ExpenseMapper.toEntity(expenseDto);
    Expense savedExpense = expenseRepository.save(expense);
    return ExpenseMapper.toDto(savedExpense);
  }

  @Override
  public ExpenseDto getExpenseById(Long id) {
    Expense expense = expenseRepository.findById(id)
        .orElseThrow(() -> new ResourseNotFoundException("Expense with id: " + id + " not found"));
    return ExpenseMapper.toDto(expense);
  }

  @Override
  public List<ExpenseDto> getAllExpenses() {
    List<Expense> expenses = expenseRepository.findAll();
    return expenses.stream().map(ExpenseMapper::toDto).toList();
  }

  @Override
  public ExpenseDto updateExpense(Long id, ExpenseDto expenseDto) {
    Expense expense = expenseRepository.findById(id)
        .orElseThrow(() -> new ResourseNotFoundException("Expense with id: " + id + " not found"));
    expense.setAmount(expenseDto.amount());
    expense.setExpenseDate(expenseDto.expenseDate());

    if (expense.getCategory() != null) {
      CategoryDto category = categoryService.updateCategory(expense.getCategory().getId(), expenseDto.categoryDto());

      expense.setCategory(CategoryMapper.toEntity(category));
    }
    Expense savedExpense = expenseRepository.save(expense);
    return ExpenseMapper.toDto(savedExpense);
  }

  @Override
  public void deleteExpense(Long id) {
    Expense expense = expenseRepository.findById(id)
        .orElseThrow(() -> new ResourseNotFoundException("Expense with id: " + id + " not found"));
    expenseRepository.delete(expense);

  }

}
