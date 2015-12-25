package com.bionic.edu.payment;


import com.bionic.edu.merchant.Merchant;
import com.bionic.edu.merchant.MerchantService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;

@Named
@Transactional(readOnly = true)
public class PaymentServiceImpl implements PaymentService{
    @Inject
    PaymentDao paymentDao;
    @Inject
    MerchantService merchantService;

    @Override
    public List<Payment> findAll() {
        return paymentDao.findAll();
    }

    @Override
    @Transactional
    public void save(int merchantId, int customerId, double sumPayed, String goods){
        Payment p = new Payment();
        Merchant m = merchantService.findById(merchantId);
        double charge = new BigDecimal((m.getCharge() * sumPayed) / 100).setScale(3, RoundingMode.HALF_UP).doubleValue();
        m.setNeedToSend(m.getNeedToSend() + sumPayed - charge);
        p.setMerchant(m);
        p.setGoods(goods);
        p.setSumPayed(sumPayed);
        p.setCustomerId(customerId);
        p.setDt(new Timestamp(System.currentTimeMillis()));
        p.setChargePayed(charge);
        paymentDao.save(p);
    }

    @Override
    public void save(Payment p) {
        paymentDao.save(p);
    }

    @Override
    public List<Payment> findByMerchantId(int id) {
        return paymentDao.findByMerchantId(id);
    }
}