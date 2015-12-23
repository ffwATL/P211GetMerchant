package com.bionic.edu.merchant.paylist;


import com.bionic.edu.merchant.Merchant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class PayListDaoImpl implements PayListDao {
    @PersistenceContext
    private EntityManager em;

    private static Logger logger = LogManager.getLogger();

    @Override
    @Transactional
    public void addPayList(PayList p) {
        Merchant m = em.find(Merchant.class, p.getMerchantId());
        m.setLastSent(new Date(System.currentTimeMillis()));
        m.setSent(m.getSent() + p.getNeedToSend());
        m.setNeedToSend(0);
        em.persist(p);
    }

    @Override
    public List<PayList> findAll() {
        TypedQuery<PayList> query = em.createQuery("SELECT p FROM PayList p", PayList.class);
        return query.getResultList();
    }

    @Override
    public List<PayList> findByMerchantId(int id) {
        TypedQuery<PayList> query = em.createQuery("SELECT p FROM PayList p WHERE p.merchantId =" + id, PayList.class);
        try {
            return query.getResultList();
        }catch (javax.persistence.NoResultException e){
            logger.trace(e.getMessage());
        }
        return null;
    }

    @Override
    public PayList findById(int id) {
        return em.find(PayList.class, id);
    }

    @Override
    @Transactional
    public List<PayList> updateAll() {
        return findAll();
    }

    @Override
    public void clearCache(){
        em.clear();
    }

    @Override
    public List<PayList> findUnPayed(){
        TypedQuery<PayList> query = em.createQuery("SELECT p FROM PayList p LEFT JOIN TransferMoney tm " +
                "ON tm.payListId = p.id WHERE tm.payListId IS NULL", PayList.class);
        return query.getResultList();
    }
}