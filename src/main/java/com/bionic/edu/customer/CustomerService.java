package com.bionic.edu.customer;


import java.util.List;

public interface CustomerService {

    Customer findById(int id);

    List<Customer> findAll();

    void save(Customer c);

    List<String> getNames(double sumPayed);

}
