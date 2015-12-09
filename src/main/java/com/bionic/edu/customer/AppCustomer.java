package com.bionic.edu.customer;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class AppCustomer {

    @Inject
    private CustomerService customerService;

    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/application-config.xml");
        AppCustomer app = (AppCustomer) ac.getBean("appCustomer");
        app.showAll();
    }

    private void showAll(){
        List<Customer> list = customerService.findAll();
        logger.trace(String.format("%-2s | %-12s | %-36s | %-20s | %-10s |", "Id","Name", "Address", "Email","Maturity"));
        logger.trace("----------------------------------------------------------------------------------------------");
        for (Customer c: list){
            logger.trace(String.format("%-2d | %-12s | %-36s | %-20s | %-10s |", c.getId(),
                    c.getName(), c.getAddress(), c.getEmail(), c.getMaturity()));
        }
    }

    private Customer findById(int id){
        return customerService.findById(id);
    }




}