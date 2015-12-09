package com.bionic.edu.payment;


import java.util.List;

public interface PaymentDao {

    public List<Payment> findAll();

    public Payment findById(int id);

    public void save(Payment p);

    public double getPaymentSum();

}
