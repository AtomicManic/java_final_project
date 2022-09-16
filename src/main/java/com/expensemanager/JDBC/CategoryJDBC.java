package com.expensemanager.JDBC;

import com.expensemanager.DAO.CategoryDAO;
import com.expensemanager.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class CategoryJDBC implements CategoryDAO {

    private static final String ADD_NEW_CATEGORY = "INSERT INTO `categories`(`name`, `user_id`) VALUES (?,?)";
    private static final String GET_CATEGORIES_BY_USER_ID = "SELECT * FROM categories WHERE user_id=?";
    private static final String GET_CATEGORY_BY_ID = "SELECT * FROM categories WHERE id=?";
    private static final String UPDATE_CATEGORY = "UPDATE `categories` SET `name`=? WHERE id=?";
    private static final String DELETE_CATEGORY = "DELETE FROM `categories` WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Category> getCategoriesByUserId(int userId) {
        return jdbcTemplate.query(GET_CATEGORIES_BY_USER_ID, (rs, rowNum)->{
            Category category = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));

            return category;

        },userId);
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return jdbcTemplate.queryForObject(GET_CATEGORY_BY_ID,(rs,rowNum)->{
            Category category = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));

            return category;

        },categoryId);
    }

    @Override
    public Category saveCategory(Category category) {
        jdbcTemplate.execute(ADD_NEW_CATEGORY, new PreparedStatementCallback<Boolean>(){

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException{

                ps.setString(1,category.getName());
                ps.setInt(2,category.getUser().getId());

                return ps.execute();
            }
        });
        return category;
    }

    @Override
    public Category updateCategory(Category category) {
        Object[] params = {category.getName(), category.getId()};
        int[] types = {Types.VARCHAR, Types.INTEGER};
        jdbcTemplate.update(UPDATE_CATEGORY, params, types);
        return category;
    }

    @Override
    public String deleteCategory(int categoryId) {
        jdbcTemplate.update(DELETE_CATEGORY,categoryId);
        return "Category deleted with id="+ categoryId;
    }
}
