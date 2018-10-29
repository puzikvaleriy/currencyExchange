package com.core.currencyExchange.controller;

import com.core.currencyExchange.entity.NbuRate;
import com.core.currencyExchange.service.NbuRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NbuController {

    @Autowired
    private NbuRateService nbuRateService;

    @RequestMapping(value = "/nbu-rates",method = RequestMethod.GET)
    @ResponseBody
    public List<NbuRate> getNbuRates(){
        return nbuRateService.getAll();
    }

    @RequestMapping(value = "/nbu-rates/{id}",method = RequestMethod.GET)
    @ResponseBody
    public NbuRate getNbuRate(@PathVariable long id) {
        return nbuRateService.getByID(id);
    }

    @RequestMapping(value = "/nbu-rates/create",method = RequestMethod.POST)
    @ResponseBody
    public void saveNbuRate(@RequestBody NbuRate nbuRate) {
       nbuRateService.create(nbuRate);
    }

    @RequestMapping(value = "/nbu-rates/update",method = RequestMethod.POST)
    @ResponseBody
    public void updateNbuRate(@RequestBody NbuRate nbuRate) {
        nbuRateService.update(nbuRate);
    }

    @RequestMapping(value = "/nbu-rates/remove/{id}",method = RequestMethod.POST)
    @ResponseBody
    public void deleteNbuRate(@PathVariable long id) {
        nbuRateService.remove(id);
    }
}
