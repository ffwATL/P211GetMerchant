package com.bionic.edu.merchant.paylist;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PayList {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private int merchantId;

    private double minSum;

    private short period;

    public double getNeedToSend() {
        return needToSend;
    }

    public void setNeedToSend(double needToSend) {
        this.needToSend = needToSend;
    }

    private double needToSend;

    public static PayList getInstance(){
        return new PayList();
    }

    public short getPeriod() {
        return period;
    }

    public void setPeriod(short period) {
        this.period = period;
    }

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

    public double getMinSum() {
        return minSum;
    }

    public void setMinSum(double minSum) {
        this.minSum = minSum;
    }



}