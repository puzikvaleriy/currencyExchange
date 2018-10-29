package com.core.currencyExchange.service;

import com.core.currencyExchange.entity.PrivatRate;

import java.util.List;

public interface PrivatRateService {
    List<PrivatRate> getAll();
    PrivatRate getByID(long id);
    boolean create(PrivatRate privatRate);
    boolean update(PrivatRate privatRate);
    boolean remove(long id);
    void fillDb() throws Exception;
}
