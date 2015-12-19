package com.bionic.edu.transfer;

import com.bionic.edu.merchant.paylist.PayList;
import com.bionic.edu.merchant.paylist.PayListService;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Timestamp;
import java.util.List;

@Named
public class TransferServiceImpl implements TransferService{
    @Inject
    PayListService payListService;

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

    public void doTransfer(List<PayList> list, int... id){
        for(int i: id){

        }

    }

    @Override
    public void doTransfer(double aSum) {
        List<PayList> pList = payListService.findUnpaid();
        for(PayList p: pList){
            if(p != null &&  aSum >= p.getNeedToSend()){
                TransferMoney tm = new TransferMoney();
                tm.setPayListId(p.getId());
                tm.setMerchantId(p.getMerchantId());
                tm.setSumSent(p.getNeedToSend());
                tm.setDt(new Timestamp(System.currentTimeMillis()));
                transferDao.save(tm);
                aSum -= p.getNeedToSend();
            }
        }
    }
}