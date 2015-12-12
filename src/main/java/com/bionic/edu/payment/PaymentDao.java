package com.bionic.edu.payment;


import com.bionic.edu.GetMerchantException;

import java.util.List;

public interface PaymentDao {

    public List<Payment> findAll();

    public Payment findById(int id);

    public void save(Payment p) throws GetMerchantException;

    public double getPaymentSum();

}
