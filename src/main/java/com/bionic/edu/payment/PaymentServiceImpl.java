package com.bionic.edu.payment;


import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class PaymentServiceImpl implements PaymentService{
    @Inject
    PaymentDao paymentDao;

    @Override
    public List<Payment> findAll() {
        return paymentDao.findAll();
    }

    @Override
    public Payment findById(int id) {
        return paymentDao.findById(id);
    }

    @Override
    @Transactional
    public void save(Payment p) {
        paymentDao.save(p);
    }
}
