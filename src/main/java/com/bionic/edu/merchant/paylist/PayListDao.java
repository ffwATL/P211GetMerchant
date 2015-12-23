package com.bionic.edu.merchant.paylist;


import java.util.List;

public interface PayListDao {

    public void addPayList(PayList p);

    public List<PayList> findAll();

    public List<PayList> findByMerchantId(int id);

    public PayList findById(int id);

    List<PayList> findUnPayed();

}