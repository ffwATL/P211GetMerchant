package com.bionic.edu.transfer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class TransferMoney {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int merchantId;

    private int payListId;

    private double sumSent;

    private Date dt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public int getPayListId() {
        return payListId;
    }

    public void setPayListId(int payListId) {
        this.payListId = payListId;
    }

    public double getSumSent() {
        return sumSent;
    }

    public void setSumSent(double sumSent) {
        this.sumSent = sumSent;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

}