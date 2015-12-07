package com.bionic.edu;

import com.bionic.edu.customer.Customer;
import com.bionic.edu.customer.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class Application{
    @Inject
    MerchantService merchantService;
    @Inject
    CustomerService customerService;

    static Logger logger = LogManager.getLogger();
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
        Application application = (Application)context.getBean("application");
        /*application.removeCustomerById(6);*/
        /*application.addMerchant();*/
        application.updateAccountMerchant(108,"new Account");
    }

    private void updateAccountMerchant(int id, String newAcc){
        merchantService.updateAccount(id, newAcc);
    }

    private void showAllCustomer(){
        logger.trace(customerService.getAllCustomer());
    }

    private void removeCustomerById(int id){
        customerService.remove(id);
    }

    private void removeMerchantById(int id){
        merchantService.removeMerchant(id);
    }

    private void findCustomer(int id){
        Customer c = customerService.findById(id);
        logger.trace(c.getName());
    }

    public int addCustomer(){
        Customer c = new Customer();
        c.setName("Mike");
        c.setAddress("something bla bla bla");
        c.setCcno("ccno");
        c.setCctype("cctype");
        c.setEmail("mike@gmail.com");
        customerService.save(c);
        return c.getId();
    }
    public int addMerchant(){
        Merchant merchant = new Merchant();
        merchant.setAccount("555555555");
        merchant.setBankName("Erste Bank");
        merchant.setCharge(1.2);
        merchant.setMinSum(145.0);
        merchant.setName("N&M");
        merchant.setPeriod((short) 1);
        merchant.setSwift("X85T44wwq");
        merchantService.save(merchant);
        return merchant.getId();
    }


    private void showAllMerchant(){
        logger.trace(merchantService.getAllMerchant());
    }

    private void addMerchant(Merchant m){
        merchantService.save(m);
        logger.info(merchantService.getAllMerchant());
    }

    public void printMerchantName(int id){
        Merchant m1 = merchantService.findById(id);
        System.out.println("name = " + m1.getName());     
    } 
}