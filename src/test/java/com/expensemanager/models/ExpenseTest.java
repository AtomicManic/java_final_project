package com.expensemanager.models;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {

    @Test
    void getId() {
        Expense ob = new Expense();
        ob.setId(1);

        assertEquals(1, ob.getId());
    }

    @Test
    void getName() {
        Expense ob = new Expense();
        ob.setName("Computer");

        assertEquals("Computer", ob.getName());
    }

    @Test
    void getAmount() {
        Expense ob = new Expense();
        ob.setAmount(3000);

        assertEquals(3000, ob.getAmount());
    }

    @Test
    void getCurrency() {
        Expense ob = new Expense();
        Expense ob1 = new Expense();
        ob.setCurrency(new Currency());
        ob1.setCurrency(new Currency());

        assertEquals(ob1.getCurrency() ,ob.getCurrency());
    }

    @Test
    void getUser() {
        Expense ob = new Expense();
        Expense ob1 = new Expense();
        ob.setUser(new User());
        ob1.setUser(new User());

        assertEquals(ob1.getUser(), ob.getUser());
    }

    @Test
    void getCategory() {
        Expense ob = new Expense();
        Expense ob1 = new Expense();
        ob.setCategory(new Category());
        ob1.setCategory(new Category());

        assertEquals(ob1.getCategory(), ob.getCategory());
    }

    @Test
    void getDate() {
        Expense ob = new Expense();
        ob.setDate("2022-08-22");

        assertEquals("2022-08-22", ob.getDate());
    }
}