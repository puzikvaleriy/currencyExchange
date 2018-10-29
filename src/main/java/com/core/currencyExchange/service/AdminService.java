package com.core.currencyExchange.service;

import com.core.currencyExchange.entity.Exchange;
import com.core.currencyExchange.entity.NbuRate;
import com.core.currencyExchange.entity.PrivatRate;

import java.util.List;

public interface AdminService {
    void getNbuRateFromJSON() throws Exception;
    void getPrivatRateFromJSON() throws Exception;

    List<Exchange> getHistory();
    Exchange getExchangeByID(long id);
    Exchange saveExchange(Exchange exchange);
    void removeExchange(long id);

    List<NbuRate> getAllNbuRates();
    NbuRate getNbuRateByID(long id);
    boolean createNbuRate(NbuRate nbuRate);
    boolean updateNbuRate(NbuRate nbuRate);
    boolean removeNbuRate(long id);

    List<PrivatRate> getAll();
    PrivatRate getPrivatRateByID(long id);
    boolean createPrivatRate(PrivatRate privatRate);
    boolean updatePrivatRate(PrivatRate privatRate);
    boolean removePrivatRate(long id);
}
