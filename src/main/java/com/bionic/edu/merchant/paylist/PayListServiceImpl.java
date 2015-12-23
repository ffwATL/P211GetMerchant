package com.bionic.edu.merchant.paylist;


import com.bionic.edu.merchant.Merchant;
import com.bionic.edu.merchant.MerchantService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Named
@Transactional(readOnly = true)
public class PayListServiceImpl implements PayListService{

    private static Logger logger = LogManager.getLogger();

    @Inject
    PayListDao payListDao;
    @Inject
    MerchantService merchantService;

    @Override
    @Transactional
    public void add(Merchant m) {
        if(m != null && m.getNeedToSend() >= m.getMinSum()){
            if(m.getLastSent() == null || validatePeriod(m.getLastSent(), m.getPeriod())){
                PayList p = PayList.getInstance();
                p.setMerchantId(m.getId());
                p.setMinSum(m.getMinSum());
                p.setPeriod(m.getPeriod());
                p.setNeedToSend(m.getNeedToSend());
                p.setDt(new Date(System.currentTimeMillis()));
                payListDao.addPayList(p);
            }
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
        try {
            return payListDao.findByMerchantId(id);
        }catch (javax.persistence.NoResultException e){
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public PayList findById(int id){
        return payListDao.findById(id);
    }

    @Override
    public void updateAll() {
        merchantService.findAll().forEach(this :: add);
    }

    @Override
    public List<PayList> findUnpaid() {
        return payListDao.findUnPayed();
    }

    @Override
    public List<PayList> findFilteredUnpaid(int a){
        List<PayList> list = findUnpaid();
        if(a == 1) return list;
        if(a == 2) Collections.sort(list);
        else if(a == 3) {
            Collections.sort(list);
            Collections.reverse(list);
        }
        validateByPeriod(list);
        return list;
    }

    @Override
    public List<List<PayList>> getGreenRedFiltered(List<PayList> payListList, double sum){
        List<PayList> green = new LinkedList<>();
        List<PayList> red = new LinkedList<>();
        List<List<PayList>> result = new LinkedList<>();
        result.add(green);
        result.add(red);
        for(PayList p: payListList){
            if(sum >= p.getNeedToSend()){
                green.add(p);
                sum -= p.getNeedToSend();
            } else red.add(p);
        }
        return result;
    }

    private void validateByPeriod(List<PayList> list){
        for(int i=0; i < list.size(); i++){
            PayList p = list.get(i);
            Merchant m = merchantService.findById(p.getMerchantId());
            if(m.getLastSent() != null){
                if(!validatePeriod(m.getLastSent(), p.getPeriod())) list.remove(i);
            }
        }
    }

    private boolean validatePeriod(java.sql.Date lastSent, short period){
        LocalDate dt = LocalDate.now();
        LocalDate ls;
        switch (period){
            case 1:
                ls = lastSent.toLocalDate().plusWeeks(1);
                break;
            case 2:
                ls = lastSent.toLocalDate().plusDays(10);
                break;
            case 3:
                ls = lastSent.toLocalDate().plusMonths(1);
                break;
            default: return false;
        }
        return dt.compareTo(ls) >= 0;
    }
}