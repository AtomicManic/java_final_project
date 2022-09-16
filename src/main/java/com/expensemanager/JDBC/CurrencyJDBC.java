package com.expensemanager.JDBC;

import com.expensemanager.DAO.CurrencyDAO;
import com.expensemanager.DAO.ExpenseDAO;
import com.expensemanager.models.Currency;
import org.hibernate.loader.collection.OneToManyJoinWalker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CurrencyJDBC implements CurrencyDAO {

    private static final String GET_ALL_CURRENCIES_QUERY = "SELECT * FROM currencies";

    private static final String GET_CURRENCY_BY_ID_QUERY = "SELECT * FROM currencies WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ExpenseDAO expenseDao;


    @Override
    public List<Currency> getAllCurrencies() {
        return jdbcTemplate.query(GET_ALL_CURRENCIES_QUERY, (rs,rowNum)->{
           Currency currency = new Currency();
           currency.setId(rs.getInt("id"));
           currency.setCurrencyName(rs.getString("name"));
           return currency;
        });
    }

    @Override
    public Currency getCurrencyById(int id) {
        return jdbcTemplate.queryForObject(GET_CURRENCY_BY_ID_QUERY,(rs,rowNum)->{
            Currency currency = new Currency();
                    currency.setId(rs.getInt("id"));
                    currency.setCurrencyName(rs.getString("name"));
                    return currency;
        },id);
    }
}
