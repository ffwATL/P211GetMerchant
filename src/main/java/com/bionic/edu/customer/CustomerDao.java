package com.bionic.edu.customer;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerDao {

    public Customer findById(int id);

    public List<Customer> getAllCustomer();

    @Transactional
    public void addCustomer(Customer c);

    @Transactional
    public void removeCustomer(int id);

}
