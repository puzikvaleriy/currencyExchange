package com.core.currencyExchange.service;

import com.core.currencyExchange.entity.Exchange;

import java.util.List;

public interface ExchangeService {
    List<Exchange> getAll();
    Exchange getByID(long id);
    Exchange save(Exchange exchange);
    //save with same id = update
    void remove(long id);
}
