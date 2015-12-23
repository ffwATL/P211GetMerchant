package com.bionic.edu.merchant;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MerchantDao {
	
	public Merchant findById(int id);

	public List<Merchant> findAll();

	@Transactional
	public void save(Merchant m);

	@Transactional
	public void remove(int id);

	@Transactional
	public void updateAccount(int id, String newAcc);

	/*public List<Result> getTotalReport();*/

	public List<Merchant> getSortedByNeedToPay();

    @Transactional
    void updateMerchant(Merchant m);

    @Transactional
    public void resetNeedToSend(Merchant m);

    public void updateSent(Merchant m, double sum);

}
