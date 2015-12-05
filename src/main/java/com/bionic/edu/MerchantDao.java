package com.bionic.edu;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MerchantDao {
	
	public Merchant findById(int id);

	public List<Merchant> getAllMerchant();

	@Transactional
	public void addMerchant(Merchant m);

	@Transactional
	public void removeMerchant(int id);

}
