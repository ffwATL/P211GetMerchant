package com.bionic.edu.merchant;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true)
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
    public void updateMerchant(Merchant m){
        em.merge(m);
    }

    @Override
    @Transactional
    public void resetNeedToSend(Merchant m){
        m.setLastSent(new Date(System.currentTimeMillis()));
        m.setNeedToSend(0);
    }

    @Override
    @Transactional
    public void updateSent(Merchant m, double sum) {
        m.setLastSent(new Date(System.currentTimeMillis()));
        m.setSent(sum);
        m.setNeedToSend(0);
        em.merge(m);
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