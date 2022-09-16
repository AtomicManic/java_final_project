package com.expensemanager.models;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getId() {
        User ob = new User();
        ob.setId(1);
        assertEquals(1, ob.getId());
    }

    @Test
    void getName() {
        User ob = new User();
        ob.setName("nitsan");
        assertEquals("nitsan", ob.getName());
    }

    @Test
    void getUserName() {
        User ob = new User();
        ob.setUserName("nitsanb");
        assertEquals("nitsanb", ob.getUserName());
    }

    @Test
    void getPassword() {
        User ob = new User();
        ob.setPassword("nbnb1212");
        assertEquals("nbnb1212", ob.getPassword());
    }

    @Test
    void getExpenses() {
        User ob = new User();

        //Create Expenses List
        //Create Expense objects
        Expense exp1 = new Expense();
        exp1.setUser(new User());
        exp1.getUser().setId(1);
        exp1.getUser().setName("Nitsan");
        exp1.getUser().setUserName("NitBY");
        exp1.getUser().setPassword("w39edj");
        exp1.setCurrency(new Currency());
        exp1.setId(1);
        exp1.setName("Food");
        exp1.setAmount(1000);
        exp1.setDate("2022-07-16");

        Expense exp2 = new Expense();
        exp2.setUser(new User());
        exp2.getUser().setId(1);
        exp2.getUser().setName("Adva");
        exp2.getUser().setUserName("Advvv");
        exp2.getUser().setPassword("123546d");
        exp2.setCurrency(new Currency());
        exp2.setId(2);
        exp2.setName("Gas");
        exp2.setAmount(500);
        exp2.setDate("2022-09-10");

        Expense exp3 = new Expense();
        exp3.setUser(new User());
        exp3.getUser().setId(1);
        exp3.getUser().setName("Nitsani");
        exp3.getUser().setUserName("Advvv");
        exp3.getUser().setPassword("23u923");
        exp3.setCurrency(new Currency());
        exp3.setId(3);
        exp3.setName("Clothes");
        exp3.setAmount(800);
        exp3.setDate("2022-06-05");

        List<Expense> expenseList = new ArrayList<Expense>();
        expenseList.add(exp1);
        expenseList.add(exp2);
        expenseList.add(exp3);

        ob.setExpenses(expenseList);
        assertEquals(expenseList, ob.getExpenses());
    }

    @Test
    void getCategories() {
        User ob = new User();

        //Create Categories List
        //Create Category objects
        Category cat1 = new Category();
        cat1.setId(1);
        cat1.setName("Electronics");
        cat1.setUser(new User());

        Category cat2 = new Category();
        cat2.setId(2);
        cat2.setName("Market");
        cat2.setUser(new User());

        Category cat3 = new Category();
        cat3.setId(3);
        cat3.setName("Luxury");
        cat3.setUser(new User());

        List<Category> categoryList = new ArrayList<Category>();
        categoryList.add(cat1);
        categoryList.add(cat2);
        categoryList.add(cat3);

        ob.setCategories(categoryList);
        assertEquals(categoryList, ob.getCategories());
    }
}