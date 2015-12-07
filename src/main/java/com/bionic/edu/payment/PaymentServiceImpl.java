package com.bionic.edu.payment;


import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class PaymentServiceImpl implements PaymentService{
    @Inject
    PaymentDao paymentDao;

    @Override
    public List<Payment> findByMerchantId(int id) {
        return paymentDao.findByMerchantId(id);
    }
}
