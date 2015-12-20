package com.bionic.edu;


import com.bionic.edu.merchant.paylist.PayList;
import com.bionic.edu.merchant.paylist.PayListService;
import com.bionic.edu.transfer.TransferMoney;
import com.bionic.edu.transfer.TransferService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class Hello {
    private static Logger logger = LogManager.getLogger();

    @Inject
    PayListService payListService;
    @Inject
    TransferService transferService;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
        Hello app = (Hello) context.getBean("hello");
        app.doTransfer(12);
    }

    private void showUnpayed(){
        List<PayList> list = payListService.findFilteredUnpaid(0);
        for(PayList p : list){
            /*Merchant m = merchantService.findById(p.getId());*/
            logger.trace("need to send: " + p.getNeedToSend());

        }
    }
    private void doTransfer(int id){
        logger.trace("starting..");
        for(TransferMoney tm: transferService.findByMerchantId(1)){
            logger.trace(tm.getSumSent());
        }

    }


}
