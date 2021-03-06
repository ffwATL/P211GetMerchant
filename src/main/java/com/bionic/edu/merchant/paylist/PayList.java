package com.bionic.edu.merchant.paylist;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class PayList implements Comparable<PayList>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int merchantId;

    private double minSum;

    private short period;

    private java.sql.Date dt;

    private double needToSend;


    public double getNeedToSend() {
        return needToSend;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public void setNeedToSend(double needToSend) {
        this.needToSend = needToSend;
    }

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

    @Override
    public int compareTo(PayList o) {
        if(this.getNeedToSend() > o.getNeedToSend()) return 1;
        else return this.getNeedToSend() < o.getNeedToSend() ? -1 : 0;
    }
}