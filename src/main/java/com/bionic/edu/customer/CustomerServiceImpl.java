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
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    @Transactional
    public void save(Customer c) {
        customerDao.save(c);
    }

    @Override
    public List<String> getNames(double sumPayed) {
        return customerDao.getNames(sumPayed);
    }
}