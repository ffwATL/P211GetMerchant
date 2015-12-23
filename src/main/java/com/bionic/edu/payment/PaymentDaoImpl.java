package com.bionic.edu.payment;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class PaymentDaoImpl implements PaymentDao {

    @PersistenceContext
    private EntityManager em;
    private static Logger logger = LogManager.getLogger();

    @Override
    public List<Payment> findAll() {
        return em.createQuery("SELECT p FROM Payment p", Payment.class).getResultList();
    }


    @Transactional
    @Override
    public void save(Payment p) {
        em.persist(p);
    }

    @Override
    public double getPaymentSum() {
        TypedQuery<Double> query = em.createQuery("SELECT SUM(p.sumPayed) FROM Payment p", Double.class);
        return query.getSingleResult();
    }

    @Override
    public List<Payment> findByMerchantId(int id) {
        TypedQuery<Payment> query = em.createQuery("SELECT p FROM Payment p WHERE p.merchant.id=" + id, Payment.class);
        return query.getResultList();
    }

    public Payment findById(int id) {
        return em.find(Payment.class, id);
    }
}