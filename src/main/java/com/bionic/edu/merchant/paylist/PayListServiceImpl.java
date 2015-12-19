package com.bionic.edu.merchant.paylist;


import com.bionic.edu.merchant.Merchant;
import com.bionic.edu.merchant.MerchantService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class PayListServiceImpl implements PayListService{

    @Inject
    PayListDao payListDao;
    @Inject
    MerchantService merchantService;

    @Override
    public void add(Merchant m) {
        if(m != null && m.getNeedToSend() >= m.getMinSum()){
            payListDao.addPayList(m);
            merchantService.resetNeedToSend(m);
        }
    }

    @Override
    public void add(int merchantId){
        Merchant m = merchantService.findById(merchantId);
        add(m);
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
        payListDao.updateAll();
        merchantService.findAll().forEach(this::add);
        return findAll();
    }

    @Override
    public List<PayList> findUnPayed() {
        return payListDao.findUnPayed();
    }
}