package com.example.expense_tracker.mapper;

import com.example.expense_tracker.dto.CategoryDto;
import com.example.expense_tracker.dto.ExpenseDto;
import com.example.expense_tracker.entity.Category;
import com.example.expense_tracker.entity.Expense;

public class ExpenseMapper {
  public static Expense toEntity(ExpenseDto expenseDto) {
    Category category = new Category();
    category.setId(expenseDto.categoryDto().id());
    return new Expense(
        expenseDto.id(),
        expenseDto.amount(),
        expenseDto.expenseDate(),
        category);
  }

  public static ExpenseDto toDto(Expense expense) {
    return new ExpenseDto(
        expense.getId(),
        expense.getAmount(),
        expense.getExpenseDate(),
        new CategoryDto(
            expense.getCategory().getId(),
            expense.getCategory().getName()));

  }
}
