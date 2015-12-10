package com.bionic.edu.customer;


import com.bionic.edu.merchant.Merchant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.List;

@Named
public class AppCustomer {

    @Inject
    private CustomerService customerService;

    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/application-config.xml");
        AppCustomer app = (AppCustomer) ac.getBean("appCustomer");
        app.getMerchants();
    }

    private void getMerchants(){
        Customer c = customerService.findById(1);
        if(c != null){
            logger.trace(c);
            Collection<Merchant> merchants = c.getMerchants();
            for (Merchant m: merchants){
                logger.trace(m.getName());
            }
        }
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