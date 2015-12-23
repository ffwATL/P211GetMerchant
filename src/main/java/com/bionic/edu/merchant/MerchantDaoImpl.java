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
		return em.createQuery("SELECT m FROM Merchant m", Merchant.class).getResultList();
	}

    @Override
    @Transactional
    public void updateMerchant(Merchant m){
        em.merge(m);
    }

	@Override
	public Merchant findById(int id){
		return em.find(Merchant.class, id);
	}
}