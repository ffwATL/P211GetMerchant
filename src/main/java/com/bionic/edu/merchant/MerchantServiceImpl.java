package com.bionic.edu.merchant;

import com.bionic.edu.result.Result;
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
	public List<Result> getTotalReport() {
		return merchantDao.getTotalReport();
	}

	@Override
	public List<Merchant> getSortedByNeedToPay() {
		return merchantDao.getSortedByNeedToPay();
	}

    @Override
    @Transactional
    public void updateNeedToSend(int id, double s) {
        merchantDao.updateNeedToSend(id, s);
    }

}