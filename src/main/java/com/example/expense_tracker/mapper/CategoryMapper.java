package com.example.expense_tracker.mapper;

import org.springframework.stereotype.Component;

import com.example.expense_tracker.dto.CategoryDto;
import com.example.expense_tracker.entity.Category;

@Component
public class CategoryMapper {
  public Category toEntity(CategoryDto categoryDto) {
    if (categoryDto == null)
      return null;
    Category category = new Category();
    category.setId(categoryDto.id());
    category.setName(categoryDto.name());
    return category;
  }

  public CategoryDto toDto(Category category) {
    if (category == null)
      return null;
    return new CategoryDto(category.getId(), category.getName());
  }
}
