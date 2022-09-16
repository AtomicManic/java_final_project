package com.expensemanager.JDBC;

import com.expensemanager.DAO.*;
import com.expensemanager.ExpenseManagerApplication;
import com.expensemanager.models.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import com.expensemanager.DAO.CurrencyDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ExpenseJDBC implements ExpenseDAO {

    private static final String GET_EXPENSES_BY_USER_ID = "SELECT * FROM expenses WHERE user_id=?";
    private static final String GET_EXPENSE_BY_ID = "SELECT * FROM expenses WHERE id=?";
    private static final String SAVE_EXPENSE = "INSERT INTO `expenses`(`name`, `amount`, `currency_id`, `user_id`, `category_id`) VALUES (?,?,?,?,?)";
    private static final String UPDATE_EXPENSE_BY_ID = "UPDATE `expenses` SET `name`=?,`amount`=?,`currency_id`=?,`user_id`=?,`category_id`=? WHERE id=?";
    private static final String REMOVE_EXPENSE = "DELETE FROM `expenses` WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CurrencyDAO currencyDao;
    private UserDAO userDao;
    private CategoryDAO categoryDao;

    public ExpenseJDBC(CurrencyDAO currencyDao, CategoryDAO categoryDao){
        this.currencyDao = currencyDao;
        this.categoryDao = categoryDao;

    }

    @Override
    public List<Expense> getExpensesByUserId(int userId) {
        return jdbcTemplate.query(GET_EXPENSES_BY_USER_ID, (rs,rowNum)->{
            Expense expense = new Expense();
            expense.setId(rs.getInt("id"));
            expense.setName(rs.getString("name"));
            expense.setAmount(rs.getFloat("amount"));
            expense.setCurrency(currencyDao.getCurrencyById(rs.getInt("currency_id")));
            expense.setCategory(categoryDao.getCategoryById(rs.getInt("category_id")));
            expense.setDate(rs.getString("date"));

            return expense;

        },userId);
    }

    @Override
    public Expense saveExpense(Expense expense) {
        jdbcTemplate.execute(SAVE_EXPENSE, new PreparedStatementCallback<Boolean>(){

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException{
                ps.setString(1,expense.getName());
                ps.setFloat(2,expense.getAmount());
                ps.setInt(3,expense.getCurrency().getId());
                ps.setInt(4,expense.getUser().getId());
                ps.setInt(5,expense.getCategory().getId());

                return ps.execute();
            }
        });
        return expense;
    }

    @Override
    public Expense getExpenseById(int id) {
        return jdbcTemplate.queryForObject(GET_EXPENSE_BY_ID, (rs,rowNum) ->{

            Expense expense = new Expense();
            expense.setId(rs.getInt("id"));
            expense.setName(rs.getString("name"));
            expense.setAmount(rs.getFloat("amount"));
            expense.setCurrency(currencyDao.getCurrencyById(rs.getInt("currency_id")));
            expense.setCategory(categoryDao.getCategoryById(rs.getInt("category_id")));
            expense.setDate(rs.getString("date"));
//
            return expense;

        },id);
    }

    @Override
    public Expense updateExpense(Expense expense) {
        Object[] params = {
                expense.getName(),
                expense.getAmount(),
                expense.getCurrency().getId(),
                expense.getUser().getId(),
                expense.getCategory().getId(),
                expense.getId()
        };
        jdbcTemplate.update(UPDATE_EXPENSE_BY_ID, params);
        return expense;
    }

    @Override
    public String deleteExpenseById(int id) {
        jdbcTemplate.update(REMOVE_EXPENSE, id);
        return "Expense deleted with id="+id;
    }
}
