package com.bionic.edu.customer;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerDao {

    public Customer findById(int id);

    public List<Customer> getAllCustomer();

    @Transactional
    public void save(Customer c);

    @Transactional
    public void remove(int id);

}
