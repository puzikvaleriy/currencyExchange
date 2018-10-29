package com.core.currencyExchange.repository;

import com.core.currencyExchange.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepo extends JpaRepository<Exchange,Long> {
}
