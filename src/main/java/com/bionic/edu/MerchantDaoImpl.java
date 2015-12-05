package com.bionic.edu;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class MerchantDaoImpl implements MerchantDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Merchant> getAllMerchant(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Merchant> cq = cb.createQuery(Merchant.class);
		Root<Merchant> rootEntry = cq.from(Merchant.class);
		CriteriaQuery<Merchant> all = cq.select(rootEntry);
		TypedQuery<Merchant> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}

	@Override
	@Transactional
	public void addMerchant(Merchant m){
		em.persist(m);
		em.flush();
	}

	@Override
	@Transactional
	public void removeMerchant(int id) {
		Merchant m = em.find(Merchant.class, id);
		em.merge(m);
		em.remove(m);
		em.flush();
	}

	@Override
	public Merchant findById(int id){
		return em.find(Merchant.class, id);
	}

}