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
public class PayListDaoImpl implements PayListDao {
    @PersistenceContext
    private EntityManager em;

    private static Logger logger = LogManager.getLogger();

    @Override
    @Transactional
    public void addPayList(Merchant m) {
        PayList p = PayList.getInstance();
        p.setMerchantId(m.getId());
        p.setMinSum(m.getMinSum());
        p.setPeriod(m.getPeriod());
        p.setNeedToSend(m.getNeedToSend());
        p.setDt(new Date(System.currentTimeMillis()));
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
    @Transactional
    public List<PayList> updateAll() {
        em.clear();
        return findAll();
    }

    @Override
    public List<PayList> findUnPayed(){
        TypedQuery<PayList> query = em.createQuery("SELECT p FROM PayList p LEFT JOIN TransferMoney tm " +
                "ON tm.payListId = p.id WHERE tm.payListId IS NULL", PayList.class);
        return query.getResultList();
    }
}