package com.core.currencyExchange.service;

import com.core.currencyExchange.entity.Exchange;
import com.core.currencyExchange.entity.NbuRate;
import com.core.currencyExchange.entity.PrivatRate;

import java.util.List;

public interface UserService {
    Exchange exchange(Exchange exchange);
    List<Exchange> getHistory();
    List<NbuRate> getNbuRate();
    List<PrivatRate> getPrivatRate();
}
