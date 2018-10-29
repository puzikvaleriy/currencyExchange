package com.core.currencyExchange.service;

import com.core.currencyExchange.entity.NbuRate;
import com.core.currencyExchange.util.ConnectionUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class NbuRateServiceImpl implements NbuRateService {

    public NbuRateServiceImpl() {
    }

    @Override
    public void fillDb() throws Exception {
        List<NbuRate> nbuRates = sendGet();
        try(Connection connection = ConnectionUtil.createConnection()){
            if(connection!=null){
                clearTable();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO nbu_rate" +
                        "(r030,txt,rate,cc,exchangedate)" +
                        " VALUES (?,?,?,?,?)");
                for (NbuRate nbuRate : nbuRates) {
                    statement.setLong(1, nbuRate.getR030());
                    statement.setString(2, nbuRate.getTxt());
                    statement.setDouble(3, nbuRate.getRate());
                    statement.setString(4, nbuRate.getCc());
                    statement.setDate(5, (Date) nbuRate.getExchangeDate());
                    statement.executeUpdate();
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    private List sendGet() throws Exception{
        List nbuRates = new ArrayList<>();
        final String url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
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
        nbuRates = objectMapper.readValue(response.toString(),new TypeReference<List<NbuRate>>(){});
        bufferedReader.close();
        return nbuRates;
    }

    private void clearTable(){
        try(Connection connection = ConnectionUtil.createConnection()){
            if(connection!=null){
                PreparedStatement statement = connection.prepareStatement("DELETE FROM nbu_rate");
                    statement.executeUpdate();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<NbuRate> getAll() {
        List<NbuRate> nbuRates = new ArrayList<>();
        try(Connection connection = ConnectionUtil.createConnection()){
            if(connection!=null){
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM nbu_rate");
                ResultSet resultSet =statement.executeQuery();
                while(resultSet.next()){
                    nbuRates.add(new NbuRate(resultSet.getLong("r030"),
                            resultSet.getString("txt"),
                            resultSet.getLong("rate"),
                            resultSet.getString("cc"),
                            resultSet.getDate("exchangedate")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nbuRates;
    }

    @Override
    public NbuRate getByID(long id) {
        NbuRate nbuRate = new NbuRate();
        try(Connection connection = ConnectionUtil.createConnection()){
            if(connection!=null){
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM nbu_rate WHERE id =?");
                statement.setLong(1,id);
                ResultSet resultSet =statement.executeQuery();
                while(resultSet.next()) {
                    nbuRate = new NbuRate(resultSet.getLong("r030"),
                            resultSet.getString("txt"),
                            resultSet.getLong("rate"),
                            resultSet.getString("cc"),
                            resultSet.getDate("exchangedate"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nbuRate;
    }

    @Override
    public boolean create(NbuRate nbuRate) {
        int count = 0;
        try (Connection connection = ConnectionUtil.createConnection()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO nbu_rate" +
                        "(r030,txt,rate,cc,exchangedate)" +
                        " VALUES (?,?,?,?,?)");
                statement.setLong(1, nbuRate.getR030());
                statement.setString(2, nbuRate.getTxt());;
                statement.setDouble(3, nbuRate.getRate());
                statement.setString(4, nbuRate.getCc());
                statement.setDate(5, (java.sql.Date)nbuRate.getExchangeDate());
                statement.executeUpdate();
                count++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return count>0;
    }

    @Override
    public boolean update(NbuRate nbuRate) {
        int count = 0;
        try (Connection connection = ConnectionUtil.createConnection()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement("UPDATE nbu_rate SET txt = ?, rate = ?, cc = ?, exchangedata = ?, WHERE r030 = ?");
                statement.setLong(1, nbuRate.getR030());
                statement.setString(2, nbuRate.getTxt());;
                statement.setDouble(3, nbuRate.getRate());
                statement.setString(4, nbuRate.getCc());
                statement.setDate(5, (java.sql.Date)nbuRate.getExchangeDate());
                statement.executeUpdate();
                count++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return count>0;
    }


    @Override
    public boolean remove(long id) {
        int counter=0;
        try (Connection connection = ConnectionUtil.createConnection()) {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement("DELETE FROM nbu_rate WHERE id=?");
                statement.setLong(1, id);
                statement.executeUpdate();
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counter>0;
    }
}
