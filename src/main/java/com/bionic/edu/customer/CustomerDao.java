package com.bionic.edu.customer;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerDao {

    public Customer findById(int id);

    public List<Customer> findAll();

    @Transactional
    public void save(Customer c);

    @Transactional
    public void remove(int id);

    public List<String> getNames(double sumPayed);


}
