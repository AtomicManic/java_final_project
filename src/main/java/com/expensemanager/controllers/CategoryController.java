package com.expensemanager.controllers;

import com.expensemanager.DAO.CategoryDAO;
import com.expensemanager.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryDAO categoryDao;

    @GetMapping("/user/{userId}/categories")
    public List<Category> getAllUserCategories(@PathVariable("userId") int userId){
        return categoryDao.getCategoriesByUserId(userId);
    }

    @GetMapping("category/{id}")
    public Category getCategory(@PathVariable("id") int id){
        return categoryDao.getCategoryById(id);
    }

    @PostMapping("/category")
    public Category saveCategory(@RequestBody Category category){
        return categoryDao.saveCategory(category);
    }

    @PutMapping("/category")
    public Category updateCategory(@RequestBody Category category){
        return categoryDao.updateCategory(category);
    }

    @DeleteMapping("category/{id}")
    public String deleteCategory(@PathVariable("id") int id) {
        return categoryDao.deleteCategory(id);
    }
}
