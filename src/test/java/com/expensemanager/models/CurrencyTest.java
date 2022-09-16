package com.expensemanager.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {

    @Test
    void getId() {
        Currency ob = new Currency();
        ob.setId(1);

        assertEquals(1, ob.getId());
    }

    @Test
    void getCurrencyName() {
        Currency ob = new Currency();
        ob.setCurrencyName("ILS");

        assertEquals("ILS", ob.getCurrencyName());
    }

}