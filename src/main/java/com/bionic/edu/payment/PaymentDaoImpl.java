package com.bionic.edu.payment;


import com.bionic.edu.GetMerchantException;
import com.bionic.edu.customer.Customer;
import com.bionic.edu.customer.NoSuchCustomerException;
import com.bionic.edu.merchant.Merchant;
import com.bionic.edu.merchant.NoSuchMerchantException;
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
    public void save(Payment p) throws GetMerchantException {
        if(em.find(Customer.class, p.getCustomerId()) != null){
            if(em.find(Merchant.class, p.getMerchantId()) != null){
                if(p.getSumPayed() < 0) throw new GetMerchantException("sum payed can't be less than 0");
                em.persist(p);
            }
            else throw new NoSuchMerchantException("No such merchant Exception! Wrong Id given: " + p.getMerchantId());
        } else {
            throw new NoSuchCustomerException("No such customer Exception! Wrong customer Id given: " + p.getCustomerId());
        }
    }

    @Override
    public double getPaymentSum() {
        TypedQuery<Double> query = em.createQuery("SELECT SUM(p.sumPayed) FROM Payment p", Double.class);
        return query.getSingleResult();
    }

    @Override
    public List<Payment> findByMerchantId(int id) {
        TypedQuery<Payment> query = em.createQuery("SELECT p FROM Payment p WHERE p.merchant.id="+id,Payment.class);
        return query.getResultList();
    }

    public Payment findById(int id){
        return em.find(Payment.class, id);
    }
}
