package com.bionic.edu;

import java.util.List;

public interface MerchantDao {
	
	public Merchant findById(int id);

	public List<Merchant> getAllMerchant();

}
