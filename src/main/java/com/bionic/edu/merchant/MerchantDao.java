package com.bionic.edu.merchant;

import java.util.List;

public interface MerchantDao {
	
	Merchant findById(int id);

	List<Merchant> findAll();

}
