package com.core.currencyExchange.service;

import com.core.currencyExchange.entity.NbuRate;

import java.util.List;

public interface NbuRateService {
    List<NbuRate> getAll();
    NbuRate getByID(long id);
    boolean create(NbuRate nbuRate);
    boolean update(NbuRate nbuRate);
    boolean remove(long id);
    void fillDb() throws Exception;
}
