package com.core.currencyExchange.service;

import com.core.currencyExchange.entity.Exchange;
import com.core.currencyExchange.entity.NbuRate;
import com.core.currencyExchange.entity.PrivatRate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    ExchangeService exchangeService;
    NbuRateService nbuRateService;
    PrivatRateService privatRateService;

    @Override
    public void getNbuRateFromJSON() throws Exception {
        nbuRateService.fillDb();
    }

    @Override
    public void getPrivatRateFromJSON() throws Exception {
        privatRateService.fillDb();
    }

    @Override
    public List<Exchange> getHistory() {
        return exchangeService.getAll();
    }

    @Override
    public Exchange getExchangeByID(long id) {
        return exchangeService.getByID(id);
    }

    @Override
    public Exchange saveExchange(Exchange exchange) {
        return exchangeService.save(exchange);
    }

    @Override
    public void removeExchange(long id) {
        exchangeService.remove(id);
    }

    @Override
    public List<NbuRate> getAllNbuRates() {
        return nbuRateService.getAll();
    }

    @Override
    public NbuRate getNbuRateByID(long id) {
        return nbuRateService.getByID(id);
    }

    @Override
    public boolean createNbuRate(NbuRate nbuRate) {
        return nbuRateService.create(nbuRate);
    }

    @Override
    public boolean updateNbuRate(NbuRate nbuRate) {
        return nbuRateService.update(nbuRate);
    }

    @Override
    public boolean removeNbuRate(long id) {
        return nbuRateService.remove(id);
    }

    @Override
    public List<PrivatRate> getAll() {
        return privatRateService.getAll();
    }

    @Override
    public PrivatRate getPrivatRateByID(long id) {
        return privatRateService.getByID(id);
    }

    @Override
    public boolean createPrivatRate(PrivatRate privatRate) {
        return privatRateService.create(privatRate);
    }

    @Override
    public boolean updatePrivatRate(PrivatRate privatRate) {
        return privatRateService.update(privatRate);
    }

    @Override
    public boolean removePrivatRate(long id) {
        return privatRateService.remove(id);
    }
}
