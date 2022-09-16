package com.expensemanager.DAO;

import com.expensemanager.models.Currency;
import java.util.List;

public interface CurrencyDAO {
    List<Currency> getAllCurrencies();
    Currency getCurrencyById(int id);
}
