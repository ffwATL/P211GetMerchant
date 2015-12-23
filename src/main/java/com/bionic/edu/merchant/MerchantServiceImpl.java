package com.bionic.edu.merchant;


import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Date;
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
	public void save(Merchant m) {
		merchantDao.save(m);
	}

    @Transactional
    public void remove(int id){
        merchantDao.remove(id);
    }

	@Override
	public void updateAccount(int id, String newAcc) {
		merchantDao.updateAccount(id, newAcc);
	}


	@Override
	public List<Merchant> getSortedByNeedToPay() {
		return merchantDao.getSortedByNeedToPay();
	}

    @Override
    @Transactional
    public void updateNeedToSend(int id, double s) {
        Merchant m = findById(id);
        m.setNeedToSend(m.getNeedToSend() + s - ((m.getCharge() * s) / 100));
    }

    @Override
    @Transactional
    public void updateSent(Merchant m, double sum) {
        m.setLastSent(new Date(System.currentTimeMillis()));
        m.setSent(sum);
        m.setNeedToSend(0);
        merchantDao.updateMerchant(m);
    }

    @Override
    public void resetNeedToSend(Merchant m) {
        merchantDao.resetNeedToSend(m);
    }

}