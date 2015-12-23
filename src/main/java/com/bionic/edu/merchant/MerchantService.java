package com.bionic.edu.merchant;


import java.util.List;

public interface MerchantService {
	
	Merchant findById(int id);

	List<Merchant> findAll();

    void updateNeedToSend(int id, double s);

}