package com.core.currencyExchange.service;

import com.core.currencyExchange.entity.Exchange;
import com.core.currencyExchange.repository.ExchangeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeServiceImpl implements ExchangeService{

    @Autowired
    private ExchangeRepo exchangeRepo;

    @Override
    public List<Exchange> getAll() {
        return exchangeRepo.findAll();
    }

    @Override
    public Exchange getByID(long id) {
        return exchangeRepo.findById(id).get();
    }

    @Override
    public Exchange save(Exchange exchange) {
        return exchangeRepo.saveAndFlush(exchange);
    }

    @Override
    public void remove(long id) {
        exchangeRepo.deleteById(id);
    }
}
