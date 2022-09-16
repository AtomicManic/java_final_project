package com.expensemanager.DAO;

import com.expensemanager.models.User;

public interface UserDAO {
    User saveUser(User user);
    User updateUser(User user);
    User getUserById(int id);
    String deleteUserById(int id);
}
