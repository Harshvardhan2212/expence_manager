package com.example.expense_tracker.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.expense_tracker.dto.CategoryDto;
import com.example.expense_tracker.entity.Category;
import com.example.expense_tracker.exception.ResourseNotFoundException;
import com.example.expense_tracker.repository.CategoryRepository;
import com.example.expense_tracker.service.CategoryService;

import lombok.AllArgsConstructor;

import com.example.expense_tracker.mapper.CategoryMapper;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  public List<CategoryDto> getAllCategories() {
    List<Category> categories = categoryRepository.findAll();

    return categories.stream()
        .map(CategoryMapper::toDto)
        .toList();
  }

  public CategoryDto findById(Long id) {

    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new ResourseNotFoundException("Category with id: " + id + " not found"));
    return CategoryMapper.toDto(category);
  }

  public CategoryDto createCategory(CategoryDto categoryDto) {
    Category category = CategoryMapper.toEntity(categoryDto);
    Category savedCategory = categoryRepository.save(category);
    return CategoryMapper.toDto(savedCategory);
  }

  public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
    Category existingCategory = categoryRepository.findById(id)
        .orElseThrow(() -> new ResourseNotFoundException("Category with id: " + id + " not found"));
    existingCategory.setName(categoryDto.name());
    categoryRepository.save(existingCategory);
    return CategoryMapper.toDto(existingCategory);
  }

  public void deleteCategory(Long id) {
    Category existingCategory = categoryRepository.findById(id)
        .orElseThrow(() -> new ResourseNotFoundException("Category with id: " + id + " not found"));
    categoryRepository.delete(existingCategory);
  }

}
