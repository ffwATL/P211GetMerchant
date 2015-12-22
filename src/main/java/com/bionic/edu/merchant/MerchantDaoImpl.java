package com.bionic.edu.merchant;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MerchantDaoImpl implements MerchantDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Merchant> findAll(){
		TypedQuery<Merchant> query = em.createQuery("SELECT m FROM Merchant m", Merchant.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void save(Merchant m){
		em.persist(m);
	}

	@Override
	@Transactional
	public void remove(int id) {
		Merchant m = findById(id);
		if(m != null) em.remove(m);
	}

	@Override
	public void updateAccount(int id, String newAcc) {
		Merchant m = findById(id);
		if(m != null) m.setAccount(newAcc);
	}

    @Override
    @Transactional
    public void updateNeedToSend(int id, double s){
        Merchant m = findById(id);
        double chargePayed = (m.getCharge() * s) / 100;
        if(m != null) m.setNeedToSend(s - chargePayed);
    }

    @Override
    @Transactional
    public void resetNeedToSend(Merchant m){
        Merchant m1 = findById(m.getId());
        m1.setNeedToSend(0);
    }

	@Override
	public List<Merchant> getSortedByNeedToPay(){
		TypedQuery<Merchant> query = em.createQuery("SELECT m FROM Merchant m ORDER BY m.needToSend", Merchant.class);
		return query.getResultList();
	}

	@Override
	public Merchant findById(int id){
		return em.find(Merchant.class, id);
	}
}