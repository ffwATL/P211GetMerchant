package com.bionic.edu.customer;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Customer findById(int id) {
        return em.find(Customer.class, id);
    }

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> tq = em.createQuery("SELECT c FROM Customer c",Customer.class);
        return tq.getResultList();
    }

    @Transactional
    @Override
    public void save(Customer c) {
        if(c != null) em.persist(c);
    }

    @Transactional
    @Override
    public void remove(int id) {
        Customer c = findById(id);
        if(c != null) em.remove(c);
    }

    @Override
    public List<String> getNames(double sumPayed) {
        String txt = "SELECT DISTINCT c.name FROM ";
        txt += "Payment p, Customer c ";
        txt += "WHERE c.id = p.customerId AND p.sumPayed > :sumPayed";
        TypedQuery<String> query = em.createQuery(txt, String.class);
        query.setParameter("sumPayed",sumPayed);
        return query.getResultList();
    }
}