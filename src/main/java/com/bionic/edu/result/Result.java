package com.bionic.edu.result;


import com.bionic.edu.payment.Payment;

import java.util.List;

public class Result {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public List<Payment> getPayments(){
        return this.payments;
    }

    private List<Payment> payments;

    private String name;
    private double sum;
    public Result(){   }
    public Result(List<Payment> payments){
        this.payments = payments;
    }
    public Result(String name, List<Payment> payments){
        this.name = name;
        this.payments = payments;
    }
    public Result(String name, double sum){
        this.name = name;
        this.sum = sum;
    }

}
