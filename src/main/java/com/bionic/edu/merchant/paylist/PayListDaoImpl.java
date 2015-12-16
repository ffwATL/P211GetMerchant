package com.bionic.edu.merchant.paylist;


import com.bionic.edu.merchant.Merchant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PayListDaoImpl implements PayListDao {
    @PersistenceContext
    private EntityManager em;

    private static Logger logger = LogManager.getLogger();

    @Override
    @Transactional
    public void addPayList(int merchantId) {
        PayList p = findByMerchantId(merchantId);
        Merchant m = em.find(Merchant.class, merchantId);
        if(m != null){
            em.refresh(m);
            if(p == null) p = PayList.getInstance();
            p.setMerchantId(merchantId);
            p.setMinSum(m.getMinSum());
            p.setPeriod(m.getPeriod());
            p.setNeedToSend(m.getNeedToSend());
            em.persist(p);
        }
    }

    @Override
    public List<PayList> findAll() {
        TypedQuery<PayList> query = em.createQuery("SELECT p FROM PayList p", PayList.class);
        return query.getResultList();
    }

    @Override
    public PayList findByMerchantId(int id) {
        TypedQuery<PayList> query = em.createQuery("SELECT p FROM PayList p WHERE p.merchantId =" + id, PayList.class);
        PayList p = null;
        try {
            p = query.getSingleResult();
        }catch (javax.persistence.NoResultException e){
            logger.trace(e.getMessage());
        }
        return p;
    }

    @Override
    @Transactional
    public List<PayList> updateAll() {
        TypedQuery<Merchant> mQuery = em.createQuery("SELECT m FROM Merchant m", Merchant.class);
        for(Merchant m: mQuery.getResultList()){
            addPayList(m.getId());
        }
        return findAll();
    }
}