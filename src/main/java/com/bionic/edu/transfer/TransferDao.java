package com.bionic.edu.transfer;


import java.util.List;

public interface TransferDao {

    List<TransferMoney> findByMerchantId(int id);

    List<TransferMoney> findAll();

    void save(TransferMoney tm);

}