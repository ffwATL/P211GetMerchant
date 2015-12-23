package com.bionic.edu.customer;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerService {

    public Customer findById(int id);

    public List<Customer> findAll();

    @Transactional
    public void save(Customer c);

    public List<String> getNames(double sumPayed);

}
