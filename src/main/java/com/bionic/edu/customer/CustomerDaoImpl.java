package com.bionic.edu.customer;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    public List<Customer> getAllCustomer() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> rootEntry = cq.from(Customer.class);
        CriteriaQuery<Customer> all = cq.select(rootEntry);
        TypedQuery<Customer> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Transactional
    @Override
    public void save(Customer c) {
        em.persist(c);
        em.flush();
    }

    @Transactional
    @Override
    public void remove(int id) {
        Customer c = findById(id);
        if(c != null){
            em.remove(c);
        }
    }

    @Override
    public List<String> getNames(double sumPayed) {
        String txt = "SELECT DISTINCT c.name FROM ";
        txt += "Payment p, Customer c " ;
        txt += "WHERE c.id = p.customerId AND p.sumPayed >:sumPayed";
        TypedQuery<String> query = em.createQuery(txt, String.class);
        return query.getResultList();
    }
}
