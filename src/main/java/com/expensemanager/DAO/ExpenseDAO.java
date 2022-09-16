package com.expensemanager.DAO;

import com.expensemanager.models.Expense;

import java.util.List;

public interface ExpenseDAO {
    List<Expense> getExpensesByUserId(int userId);

    Expense saveExpense(Expense expense);

    Expense getExpenseById(int id);

    Expense updateExpense(Expense expense);

    String deleteExpenseById(int id);
}
