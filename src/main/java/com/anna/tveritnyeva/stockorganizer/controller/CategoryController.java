package com.anna.tveritnyeva.stockorganizer.controller;

import com.anna.tveritnyeva.stockorganizer.beans.Section;
import com.anna.tveritnyeva.stockorganizer.documents.Category;
import com.anna.tveritnyeva.stockorganizer.exception.CategoryException;
import com.anna.tveritnyeva.stockorganizer.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("addCategory")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category) throws CategoryException {
        System.out.println("addCategory: " + category);
        return categoryService.addCategory(category);
    }

    @PostMapping("updateCategory")
    @ResponseStatus(HttpStatus.OK)
    public Category updateCategory(@RequestBody Category category) throws CategoryException {
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("deleteCategory/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@RequestParam String categoryId) throws CategoryException {
        categoryService.deleteCategoryById(categoryId);
    }

    @GetMapping("getAllCategories")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("addSection")
    @ResponseStatus(HttpStatus.OK)
    public Category addSection(@RequestParam String categoryId, @RequestBody Section section) throws CategoryException {
        return categoryService.addSection(categoryId, section);
    }
}
