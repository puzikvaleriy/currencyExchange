package com.core.currencyExchange.util;

import com.core.currencyExchange.entity.PrivatRate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrivatRateMapper implements RowMapper<PrivatRate> {

    public PrivatRate mapRow(ResultSet resultSet, int i) throws SQLException{
        PrivatRate privatRate = new PrivatRate();
        privatRate.setId(resultSet.getLong("id"));
        privatRate.setCcy(resultSet.getString("ccy"));
        privatRate.setBase_ccy(resultSet.getString("base_ccy"));
        privatRate.setBuy(resultSet.getLong("buy"));
        privatRate.setSale(resultSet.getLong("sale"));
        return privatRate;
    }
}
