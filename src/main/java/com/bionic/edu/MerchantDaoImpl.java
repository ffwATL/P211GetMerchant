package com.bionic.edu;

import com.bionic.edu.result.Result;
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
	public void save(Merchant m){
		em.persist(m);
		em.flush();
	}

	@Override
	@Transactional
	public void removeMerchant(int id) {
		Merchant m = em.find(Merchant.class, id);
		if(m != null){
			em.remove(m);
		}
	}

	@Override
	public void updateAccount(int id, String newAcc) {
		Merchant m = em.find(Merchant.class, id);
		if(m != null){
			m.setAccount(newAcc);
		}
	}

	@Override
	public List<Result> getTotalReport() {
		String txt = "SELECT new com.bionic.edu.result.Result (m.name, SUM(p.chargePayed))";
		txt += "FROM Payment p, Merchant m WHERE m.id = p.merchantId GROUP BY m.name";
		TypedQuery<Result> query = em.createQuery(txt, Result.class);
		return query.getResultList();
	}

	@Override
	public Merchant findById(int id){
		return em.find(Merchant.class, id);
	}

}