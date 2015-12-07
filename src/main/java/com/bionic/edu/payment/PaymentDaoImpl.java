package com.bionic.edu.payment;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PaymentDaoImpl implements PaymentDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Payment> findByMerchantId(int id){
        TypedQuery<Payment> query = em.createQuery("SELECT p FROM Payment p WHERE p.merchantId ="+ id, Payment.class);
        return query.getResultList();
    }

    public Payment findById(int id){
        return em.find(Payment.class, id);
    }

}
