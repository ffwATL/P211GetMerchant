package com.bionic.edu.payment;


import java.util.List;

public interface PaymentService {

    public List<Payment> findByMerchantId(int id);

    public Payment findById(int id);


}
