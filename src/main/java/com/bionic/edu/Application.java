package com.bionic.edu;

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
    Logger logger = LogManager.getLogger();
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
        Application application = (Application)context.getBean("application");
        /*application.printMerchantName(2);*/
        application.showAll();
     }

    private void showAll(){
        logger.trace(merchantService.getAllMerchant());
    }
    public void printMerchantName(int id){
        Merchant m1 = merchantService.findById(id);
        System.out.println("name = " + m1.getName());     
    } 
}
