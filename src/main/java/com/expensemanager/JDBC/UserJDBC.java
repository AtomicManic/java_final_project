package com.expensemanager.JDBC;

import com.expensemanager.DAO.CategoryDAO;
import com.expensemanager.DAO.ExpenseDAO;
import com.expensemanager.DAO.UserDAO;
import com.expensemanager.models.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;


@Repository
public class UserJDBC implements UserDAO {

    private static final String INSERT_USER_QUERY = "INSERT INTO `users`(`name`, `username`, `password`) VALUES (?,?,?);";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET `name`=?,`username`=?,`password`=? WHERE id=?";
    private static final String GET_USER_QUERY = "SELECT * FROM users WHERE id=?";
    private static final String REMOVE_USER_QUERY = "DELETE FROM `users` WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CategoryDAO categoryDao;
    private ExpenseDAO expenseDao;

    public UserJDBC(CategoryDAO categoryDao, ExpenseDAO expenseDao){
        this.categoryDao = categoryDao;
        this.expenseDao = expenseDao;
    }

    @Override
    public User saveUser(User user) {
        jdbcTemplate.execute(INSERT_USER_QUERY, new PreparedStatementCallback<Boolean>() {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException{
                ps.setString(1,user.getName());
                ps.setString(2, user.getUserName());
                ps.setString(3, user.getPassword());

                return ps.execute();
            }
        });
        return user;
    }

    @Override
    public User updateUser(User user) {
        Object[] params = {user.getName(),user.getUserName(),user.getPassword(),user.getId()};
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
        jdbcTemplate.update(UPDATE_USER_QUERY, params, types);
        return user;
    }

    @Override
    public User getUserById(int id) {
        return jdbcTemplate.queryForObject(GET_USER_QUERY,(rs,rowNum) -> {
            return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("username"),
                    rs.getString("password"),
                    expenseDao.getExpensesByUserId(rs.getInt("id")),
                    categoryDao.getCategoriesByUserId(id)
                    );
        },id);
    }

    @Override
    public String deleteUserById(int id) {
        jdbcTemplate.update(REMOVE_USER_QUERY,id);
        return "User deleted with id=" + id;
    }
}
