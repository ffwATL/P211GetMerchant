package com.bionic.edu.customer;


import java.util.List;

public interface CustomerDao {

    Customer findById(int id);

    List<Customer> findAll();

    void save(Customer c);

    void remove(int id);

    List<String> getNames(double sumPayed);

}
