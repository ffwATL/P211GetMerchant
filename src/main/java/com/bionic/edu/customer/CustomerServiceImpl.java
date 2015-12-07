package com.bionic.edu.customer;


import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class CustomerServiceImpl implements CustomerService {

    @Inject
    private CustomerDao customerDao;

    @Override
    public Customer findById(int id) {
        return customerDao.findById(id);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerDao.getAllCustomer();
    }

    @Override
    @Transactional
    public void addCustomer(Customer c) {
        customerDao.addCustomer(c);
    }

    @Override
    @Transactional
    public void removeCustomer(int id) {
        customerDao.removeCustomer(id);
    }
}