package com.bionic.edu.merchant.paylist;


import com.bionic.edu.merchant.Merchant;
import com.bionic.edu.merchant.MerchantService;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.util.Collections;
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
    public PayList findById(int id){
        return payListDao.findById(id);
    }

    @Override
    public List<PayList> updateAll() {
        payListDao.updateAll();
        merchantService.findAll().forEach(this::add);
        return findAll();
    }

    @Override
    public List<PayList> findUnpaid() {
        return payListDao.findUnPayed();
    }

    @Override
    public List<PayList> findFilteredUnpaid(int a){
        return sortList(a);
    }

    private List<PayList> sortList(int a){
        List<PayList> list = findUnpaid();
        if(a == 2) Collections.sort(list);
        else if(a == 3) {
            Collections.sort(list);
            Collections.reverse(list);
        }
        validateByPeriod(list);
        return list;
    }

    private void validateByPeriod(List<PayList> list){
        for(int i=0; i<list.size(); i++){
            PayList p = list.get(i);
            Merchant m = merchantService.findById(p.getMerchantId());
            if(m.getLastSent() != null){
                if(!validatePeriod(m.getLastSent(), p.getPeriod())) list.remove(p);
            }
        }
    }

    private boolean validatePeriod(java.sql.Date lastSent, short period){
        switch (period){
            case 1:
                return LocalDate.now().getDayOfWeek().getValue() == 1;
            case 2:
                LocalDate dt = LocalDate.now();
                LocalDate ls = lastSent.toLocalDate();
                ls.plusDays(10);
                return lastSent.toLocalDate().getMonthValue() == dt.getMonthValue() ? ls.getDayOfMonth() <= dt.getDayOfMonth() : false;
            case 3:
                return LocalDate.now().getDayOfMonth() == 1;
            default: return false;
        }
    }
}