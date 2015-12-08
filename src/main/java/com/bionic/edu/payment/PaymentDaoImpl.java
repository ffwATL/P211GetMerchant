package com.bionic.edu.payment;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PaymentDaoImpl implements PaymentDao{

    @PersistenceContext
    private EntityManager em;

    @Override
        public List<Payment> findAll(){
        TypedQuery<Payment> query = em.createQuery("SELECT p FROM Payment p", Payment.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void save(Payment p){
        em.persist(p);
    }

    public Payment findById(int id){
        return em.find(Payment.class, id);
    }
}
