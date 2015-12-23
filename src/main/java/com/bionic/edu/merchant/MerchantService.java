package com.bionic.edu.merchant;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MerchantService {
	
	public Merchant findById(int id);

	public List<Merchant> findAll();

    @Transactional
    public void save(Merchant m);

    @Transactional
    public void remove(int id);

    @Transactional
    public void updateAccount(int id, String newAcc);

    public List<Merchant> getSortedByNeedToPay();

    @Transactional
    public void updateNeedToSend(int id, double s);

    @Transactional
    public void updateSent(Merchant m, double sum);

    @Transactional
    public void resetNeedToSend(Merchant m);

}