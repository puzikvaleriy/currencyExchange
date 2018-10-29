package com.core.currencyExchange.controller;

import com.core.currencyExchange.entity.PrivatRate;
import com.core.currencyExchange.service.PrivatRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrivatController {

    @Autowired
    private PrivatRateService privatRateService;

    @RequestMapping(value = "/privat-rates",method = RequestMethod.GET)
    @ResponseBody
    public List<PrivatRate> getPrivatRates(){
        return privatRateService.getAll();
    }

    @RequestMapping(value = "/privat-rates/{id}",method = RequestMethod.GET)
    @ResponseBody
    public PrivatRate getPrivatRate(@PathVariable long id) {
        return privatRateService.getByID(id);
    }
    @RequestMapping(value = "/privat-rates/create",method = RequestMethod.POST)
    @ResponseBody
    public void savePrivatRate(@RequestBody PrivatRate privatRate) {
        privatRateService.create(privatRate);
    }

    @RequestMapping(value = "/privat-rates/update",method = RequestMethod.POST)
    @ResponseBody
    public void updatePrivatRate(@RequestBody PrivatRate privatRate) {
        privatRateService.update(privatRate);
    }


    @RequestMapping(value = "/privat-rates/remove/{id}",method = RequestMethod.POST)
    @ResponseBody
    public void deletePrivatRate(@PathVariable long id) {
        privatRateService.remove(id);
    }
}
