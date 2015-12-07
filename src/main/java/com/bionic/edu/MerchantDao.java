package com.bionic.edu;

import com.bionic.edu.result.Result;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MerchantDao {
	
	public Merchant findById(int id);

	public List<Merchant> getAllMerchant();

	@Transactional
	public void save(Merchant m);

	@Transactional
	public void removeMerchant(int id);

	@Transactional
	public void updateAccount(int id, String newAcc);

	public List<Result> getTotalReport();


}
