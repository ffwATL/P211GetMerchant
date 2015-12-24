package com.bionic.edu.transfer;


import java.util.List;

public interface TransferService {

    List<TransferMoney> findByMerchantId(int id);

    List<TransferMoney> findAll();

    void doTransfer(int... id);

}