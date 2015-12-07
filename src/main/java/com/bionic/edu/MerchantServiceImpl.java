package com.bionic.edu;

import org.springframework.transaction.annotation.Transactional;

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

	@Override
	@Transactional
	public void save(Merchant m) {
		merchantDao.save(m);
	}

    @Transactional
    public void removeMerchant(int id){
        merchantDao.removeMerchant(id);
    }

}
