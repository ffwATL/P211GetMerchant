package com.bionic.edu;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class MerchantServiceImpl implements MerchantService{
	
	@Inject
	private MerchantDao merchantDao;
	
	public Merchant findById(int id){
		return merchantDao.findById(id);
	}

	@Override
	public List<Merchant> getAllMerchant() {
		return merchantDao.getAllMerchant();
	}

}
