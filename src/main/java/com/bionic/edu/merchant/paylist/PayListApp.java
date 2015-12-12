package com.bionic.edu.merchant.paylist;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class PayListApp {
    @Inject
    PayListService payListService;

    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
        PayListApp app = (PayListApp) context.getBean("payListApp");
        app.add(12);
        app.showAll();

    }

    private void showAll(){
        List<PayList> list = payListService.findAll();
        for(PayList p: list){
            logger.trace(p.getId()+" " + p.getMerchantId()+ " " + p.getPeriod()+" "+ p.getMinSum());
        }
    }

    private void add(int merchantId){
        payListService.addPayList(merchantId);
    }

}