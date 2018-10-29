package com.core.currencyExchange.service;

import com.core.currencyExchange.entity.Exchange;
import com.core.currencyExchange.entity.NbuRate;
import com.core.currencyExchange.entity.PrivatRate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    ExchangeService exchangeService;
    PrivatRateService privatRateService;
    NbuRateService nbuRateService;

    @Override
    public Exchange exchange(Exchange exchange) {
        return exchangeService.save(exchange);
    }


    @Override
    public List<Exchange> getHistory() {
        return exchangeService.getAll();
    }

    @Override
    public List<NbuRate> getNbuRate() {
        return nbuRateService.getAll();
    }

    @Override
    public List<PrivatRate> getPrivatRate() {
        return privatRateService.getAll();
    }
}
