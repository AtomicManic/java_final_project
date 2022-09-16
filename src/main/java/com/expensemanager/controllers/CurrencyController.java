package com.expensemanager.controllers;

import com.expensemanager.DAO.CurrencyDAO;
import com.expensemanager.models.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurrencyController {

    @Autowired
    CurrencyDAO currencyDao;

    @GetMapping("/currencies")
    public List<Currency> getAllCurrencies(){
        return currencyDao.getAllCurrencies();
    }

    @GetMapping("/currency/{id}")
    public Currency getCurrency(@PathVariable("id") int id){
        return currencyDao.getCurrencyById(id);
    }
}
