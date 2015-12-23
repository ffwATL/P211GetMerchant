package com.bionic.edu.merchant;


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
	public List<Merchant> findAll() {
		return merchantDao.findAll();
	}

    @Override
    @Transactional
    public void updateNeedToSend(int id, double s) {
        Merchant m = findById(id);
        m.setNeedToSend(m.getNeedToSend() + s - ((m.getCharge() * s) / 100));
    }

}