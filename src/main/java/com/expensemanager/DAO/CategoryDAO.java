package com.expensemanager.DAO;

import com.expensemanager.models.Category;
import java.util.List;

public interface CategoryDAO {
    List<Category> getCategoriesByUserId(int userId);
    Category getCategoryById(int categoryId);

    Category saveCategory(Category category);
    Category updateCategory(Category category);
    String deleteCategory(int categoryId);
}
