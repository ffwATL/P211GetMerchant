package com.bionic.edu.merchant.paylist;


import java.util.List;

public interface PayListDao {

    public void addPayList(int merchantId);

    public List<PayList> findAll();

    public List<PayList> findByMerchantId(int id);

    public List<PayList> updateAll();
}