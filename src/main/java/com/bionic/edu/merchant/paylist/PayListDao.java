package com.bionic.edu.merchant.paylist;


import java.util.List;

public interface PayListDao {

    void addPayList(PayList p);

    List<PayList> findAll();

    List<PayList> findByMerchantId(int id);

    PayList findById(int id);

    List<PayList> findUnPayed();

}