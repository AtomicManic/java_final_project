package com.expensemanager.controllers;

import com.expensemanager.DAO.ExpenseDAO;
import com.expensemanager.models.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    ExpenseDAO expenseDao;

    @GetMapping("/user/{id}/expenses")
    public List<Expense> getUserExpenses(@PathVariable("id") int userId){
        return expenseDao.getExpensesByUserId(userId);
    }

    @GetMapping("/expense/{id}")
    public Expense getExpenseById(@PathVariable("id") int id){
        return expenseDao.getExpenseById(id);
    }

    @PostMapping("/expense")
    public Expense saveExpense(@RequestBody Expense expense){
        return expenseDao.saveExpense(expense);
    }

    @PutMapping("/expense")
    public Expense updateExpense(@RequestBody Expense expense){
        return expenseDao.updateExpense(expense);
    }

    @DeleteMapping("/expense/{id}")
    public String deleteExpense(@PathVariable("id") int id){
        return expenseDao.deleteExpenseById(id);
    }
}
