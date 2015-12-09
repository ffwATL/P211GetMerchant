package com.bionic.edu.payment;


import com.bionic.edu.customer.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static Logger logger = LogManager.getLogger();

    @Override
        public List<Payment> findAll(){
        TypedQuery<Payment> query = em.createQuery("SELECT p FROM Payment p", Payment.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void save(Payment p){
        if(em.find(Customer.class, p.getCustomerId()) != null){
            if(em.find(Customer.class, p.getMerchantId())!= null) em.persist(p);
            else logger.warn("wrong merchant Id given: " + p.getMerchantId());
        } else logger.warn("Wrong customer Id given: " + p.getCustomerId());
    }

    @Override
    public double getPaymentSum() {
        TypedQuery<Double> query = em.createQuery("SELECT SUM(p.sumPayed) FROM Payment p", Double.class);
        return query.getSingleResult();
    }

    public Payment findById(int id){
        return em.find(Payment.class, id);
    }
}
