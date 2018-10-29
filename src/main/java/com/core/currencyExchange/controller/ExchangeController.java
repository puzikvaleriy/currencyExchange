package com.core.currencyExchange.controller;

import com.core.currencyExchange.entity.Exchange;
import com.core.currencyExchange.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @RequestMapping(value = "/exchanges",method = RequestMethod.GET)
    @ResponseBody
    public List<Exchange> getExchanges(){
        return exchangeService.getAll();
    }

    @RequestMapping(value = "/exchanges/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Exchange getExchange(@PathVariable long id){
        return exchangeService.getByID(id);
    }

    @RequestMapping(value = "/exchanges",method = RequestMethod.POST)
    @ResponseBody
    public Exchange saveExchange(@RequestBody Exchange exchange){
        return exchangeService.save(exchange);
    }

    @RequestMapping(value = "/exchanges/{id}",method = RequestMethod.POST)
    @ResponseBody
    public void deleteExchange(@PathVariable long id){
        exchangeService.remove(id);
    }
}
