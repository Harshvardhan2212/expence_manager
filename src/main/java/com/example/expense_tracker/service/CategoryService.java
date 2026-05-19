package com.example.expense_tracker.service;

import java.util.List;

import com.example.expense_tracker.dto.CategoryDto;

public interface CategoryService {
  List<CategoryDto> getAllCategories();

  CategoryDto findById(Long id);

  CategoryDto createCategory(CategoryDto categoryDto);

  CategoryDto updateCategory(Long id, CategoryDto categoryDto);

  void deleteCategory(Long id);
}
