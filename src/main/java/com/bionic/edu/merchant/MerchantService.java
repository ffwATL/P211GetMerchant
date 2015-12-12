package com.bionic.edu.merchant;

import com.bionic.edu.result.Result;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MerchantService {
	
	public Merchant findById(int id);

	public List<Merchant> findAll();

    @Transactional
    public void save(Merchant m);

    @Transactional
    public void remove(int id);

    @Transactional
    public void updateAccount(int id, String newAcc);

    public List<Result> getTotalReport();

    public List<Merchant> getSortedByNeedToPay();

    @Transactional
    public void updateNeedToSend(int id, double s);

}