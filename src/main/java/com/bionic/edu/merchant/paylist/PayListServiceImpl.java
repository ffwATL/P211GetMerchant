package com.bionic.edu.merchant.paylist;


import com.bionic.edu.merchant.Merchant;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class PayListServiceImpl implements PayListService{
    @Inject
    PayListDao payListDao;

    @Override
    public void addPayList(Merchant m) {
        payListDao.addPayList(m);
    }

    @Override
    public List<PayList> findAll() {
        return payListDao.findAll();
    }

    @Override
    public List<PayList> findByMerchantId(int id) {
        return payListDao.findByMerchantId(id);
    }

    @Override
    public List<PayList> updateAll() {
        return payListDao.updateAll();
    }
}