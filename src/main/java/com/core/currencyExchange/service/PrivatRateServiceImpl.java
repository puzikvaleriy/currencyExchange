package com.core.currencyExchange.service;

import com.core.currencyExchange.entity.PrivatRate;
import com.core.currencyExchange.util.PrivatRateMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrivatRateServiceImpl implements PrivatRateService{

    private JdbcTemplate jdbcTemplate;

    private final String SQL_FIND = "select * from privat_rate where id = ?";
    private final String SQL_DELETE = "delete from privat_rate where id = ?";
    private final String SQL_UPDATE = "update privat_rate set ccy = ?, base_ccy = ?, buy  = ?, sale = ? where id = ?";
    private final String SQL_GET_ALL = "select * from privat_rate";
    private final String SQL_INSERT = "insert into privat_rate(id, ccy, base_ccy, buy, sale) values(?,?,?,?,?)";

    private DataSource dataSource;

    public PrivatRateServiceImpl(DataSource dataSource){
        this.dataSource=dataSource;
    }

    private List sendGet() throws Exception{
        List<PrivatRate> privatRates = new ArrayList<>();
        final String url = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        while ((inputLine = bufferedReader.readLine())!=null){
            response.append(inputLine);
        }
        privatRates = objectMapper.readValue(response.toString(),new TypeReference<List<PrivatRate>>(){});
        bufferedReader.close();
        return privatRates;
    }

    private void clearTable(){
        jdbcTemplate.update("DELETE FROM privat_rate");
    }

    @Override
    @Autowired
    public void fillDb() throws Exception {
        jdbcTemplate = new JdbcTemplate(dataSource);
        List<PrivatRate> privatRates = sendGet();
        clearTable();
        int counter = 0;
        for (PrivatRate privatRate : privatRates) {
            jdbcTemplate.update(SQL_INSERT, counter++, privatRate.getCcy(),
                    privatRate.getBase_ccy(), privatRate.getBuy(), privatRate.getSale());
        }
    }

    @Override
    public List<PrivatRate> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new PrivatRateMapper());
    }

    @Override
    public PrivatRate getByID(long id) {
        return jdbcTemplate.queryForObject(SQL_FIND, new Object[] { id }, new PrivatRateMapper());
    }

    @Override
    public boolean create(PrivatRate privatRate) {
        return jdbcTemplate.update(SQL_INSERT, privatRate.getId(), privatRate.getCcy(),
                privatRate.getBase_ccy(), privatRate.getBuy(),
                privatRate.getSale()) > 0;
    }

    @Override
    public boolean update(PrivatRate privatRate) {
        return jdbcTemplate.update(SQL_UPDATE, privatRate.getCcy(),
                privatRate.getBase_ccy(), privatRate.getBuy(),
                privatRate.getSale(), privatRate.getId()) > 0;
    }

    @Override
    public boolean remove(long id) {
        return jdbcTemplate.update(SQL_DELETE, id) > 0;
    }
}
