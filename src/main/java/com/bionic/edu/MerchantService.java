package com.bionic.edu;

import java.util.List;

public interface MerchantService {
	
	public Merchant findById(int id);

	public List<Merchant> getAllMerchant();

}
