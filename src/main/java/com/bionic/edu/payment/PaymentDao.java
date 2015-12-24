package com.bionic.edu.payment;


import java.util.List;

public interface PaymentDao {

    List<Payment> findAll();

    Payment findById(int id);

    void save(Payment p);

    List<Payment> findByMerchantId(int id);

}