package com.bionic.edu.merchant.paylist;


import com.bionic.edu.merchant.Merchant;

import java.util.List;

public interface PayListService {

    public void addPayList(Merchant m);

    public List<PayList> findAll();

    public List<PayList> findByMerchantId(int id);

    public List<PayList> updateAll();
}
