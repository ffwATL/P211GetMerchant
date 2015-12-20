package com.bionic.edu.transfer;

import com.bionic.edu.merchant.MerchantService;
import com.bionic.edu.merchant.paylist.PayList;
import com.bionic.edu.merchant.paylist.PayListService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Timestamp;
import java.util.List;

@Named
public class TransferServiceImpl implements TransferService{
    @Inject
    PayListService payListService;

    @Inject
    MerchantService merchantService;

    @Inject
    TransferDao transferDao;

    @Override
    public List<TransferMoney> findByMerchantId(int id) {
        return transferDao.findByMerchantId(id);
    }

    @Override
    public List<TransferMoney> findAll() {
        return transferDao.findAll();
    }

    @Override
    @Transactional
    public void doTransfer(int... id){
        for(int a: id){
            PayList p = payListService.findById(a);
            save(p);
            merchantService.findById(p.getMerchantId()).setLastSent(new java.sql.Date(System.currentTimeMillis()));
        }
    }

    @Transactional
    private void save(PayList p){
        TransferMoney tm = new TransferMoney();
        tm.setPayListId(p.getId());
        tm.setMerchantId(p.getMerchantId());
        tm.setSumSent(p.getNeedToSend());
        tm.setDt(new Timestamp(System.currentTimeMillis()));
        transferDao.save(tm);
    }
}