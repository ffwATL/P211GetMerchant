package com.bionic.edu.customer;


import java.util.List;

public interface CustomerDao {

    public Customer findById(int id);

    public List<Customer> findAll();

    public void save(Customer c);

    public void remove(int id);

    public List<String> getNames(double sumPayed);

}
