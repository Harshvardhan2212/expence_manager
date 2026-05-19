package com.example.expense_tracker.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense_tracker.dto.CategoryDto;
import com.example.expense_tracker.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public ResponseEntity<List<CategoryDto>> getAllCategories() {
    List<CategoryDto> categories = categoryService.getAllCategories();
    return ResponseEntity.ok(categories);
  }

  @PostMapping
  public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
    CategoryDto createdCategory = categoryService.createCategory(categoryDto);
    return ResponseEntity.ok(createdCategory);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
    CategoryDto category = categoryService.updateCategory(id, categoryDto);
    return ResponseEntity.ok(category);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.ok(Map.of("Message", "Category Deleted successfully"));
  }
}
