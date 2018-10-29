package com.core.currencyExchange.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "nbu_rate")
public class NbuRate {

    @Id
    private long r030;

    @Column(nullable = false)
    private String txt;

    @Column(nullable = false)
    private double rate;

    @Column(nullable = false)
    private String cc;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date exchangedate;

    public NbuRate() {
    }

    public NbuRate(long r030, String txt, double rate, String cc, Date exchangedate) {
        this.r030 = r030;
        this.txt = txt;
        this.rate = rate;
        this.cc = cc;
        this.exchangedate = exchangedate;
    }

    public long getR030() {
        return r030;
    }

    public void setR030(long r030) {
        this.r030 = r030;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public Date getExchangeDate() {
        return exchangedate;
    }

    public void setExchangeDate(Date exchangeDate) {
        this.exchangedate = exchangeDate;
    }
}
