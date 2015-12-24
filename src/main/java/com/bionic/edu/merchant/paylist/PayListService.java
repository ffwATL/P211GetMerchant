package com.bionic.edu.merchant.paylist;


import com.bionic.edu.merchant.Merchant;

import java.util.List;

public interface PayListService {

    void add(Merchant m);

    void add(int merchantId);

    List<PayList> findAll();

    List<PayList> findByMerchantId(int id);

    PayList findById(int id);

    void updateAll();

    List<PayList> findUnpaid();

    List<PayList> findFilteredUnpaid(int a);

    List<List<PayList>> getGreenRedFiltered(List<PayList> payListList, double sum);

}
