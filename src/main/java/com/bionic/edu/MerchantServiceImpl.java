package com.bionic.edu;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class MerchantServiceImpl implements MerchantService{
	
	@Inject
	private MerchantDao merchantDao;
	
	public Merchant findById(int id){
		return merchantDao.findById(id);
	}

}
