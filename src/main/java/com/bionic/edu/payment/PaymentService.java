package com.bionic.edu.payment;


import java.util.List;

public interface PaymentService {

    List<Payment> findAll();

    List<Payment> findByMerchantId(int id);

    void save(int merchantId, int customerId, double sumPayed, String goods);
    void save(Payment p);
}