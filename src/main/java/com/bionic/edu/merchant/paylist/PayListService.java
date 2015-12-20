package com.bionic.edu.merchant.paylist;


import com.bionic.edu.merchant.Merchant;

import java.util.List;

public interface PayListService {

    public void add(Merchant m);

    public void add(int merchantId);

    public List<PayList> findAll();

    public List<PayList> findByMerchantId(int id);

    public PayList findById(int id);

    public List<PayList> updateAll();

    List<PayList> findUnpaid();

    public List<PayList> findFilteredUnpaid(int a);
}
