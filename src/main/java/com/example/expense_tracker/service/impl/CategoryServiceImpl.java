package com.example.expense_tracker.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.expense_tracker.dto.CategoryDto;
import com.example.expense_tracker.entity.Category;
import com.example.expense_tracker.exception.CategoryNotFoundException;
import com.example.expense_tracker.repository.CategoryRepository;
import com.example.expense_tracker.service.CategoryService;
import com.example.expense_tracker.mapper.CategoryMapper;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryMapper categoryMapper;

  private final CategoryRepository categoryRepository;

  public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
    this.categoryMapper = categoryMapper;
    this.categoryRepository = categoryRepository;
  }

  public List<CategoryDto> getAllCategories() {
    List<Category> categories = categoryRepository.findAll();

    return categories.stream()
        .map(categoryMapper::toDto)
        .toList();
  }

  public CategoryDto findById(Long id) {

    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new CategoryNotFoundException(id));
    return categoryMapper.toDto(category);
  }

  public CategoryDto createCategory(CategoryDto categoryDto) {
    Category category = categoryMapper.toEntity(categoryDto);
    Category savedCategory = categoryRepository.save(category);
    return categoryMapper.toDto(savedCategory);
  }

  public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
    Category existingCategory = categoryRepository.findById(id)
        .orElseThrow(() -> new CategoryNotFoundException(id));
    existingCategory.setName(categoryDto.name());
    categoryRepository.save(existingCategory);
    return categoryMapper.toDto(existingCategory);
  }

  public void deleteCategory(Long id) {
    Category existingCategory = categoryRepository.findById(id)
        .orElseThrow(() -> new CategoryNotFoundException(id));
    categoryRepository.delete(existingCategory);
  }

}
