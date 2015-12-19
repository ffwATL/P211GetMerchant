package com.bionic.edu.transfer;


import com.bionic.edu.merchant.paylist.PayList;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TransferDaoImpl implements TransferDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<TransferMoney> findByMerchantId(int id) {
        TypedQuery<TransferMoney> query = em.createQuery("SELECT tm FROM TransferMoney tm WHERE tm.id=" + id, TransferMoney.class);
        return query.getResultList();
    }

    @Override
    public List<TransferMoney> findAll() {
        TypedQuery<TransferMoney> query = em.createQuery("SELECT tm FROM TransferMoney tm", TransferMoney.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(TransferMoney tm) {
        em.persist(tm);
    }


}
