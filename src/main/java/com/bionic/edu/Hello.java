package com.bionic.edu;


import com.bionic.edu.merchant.paylist.PayList;
import com.bionic.edu.merchant.paylist.PayListService;
import com.bionic.edu.transfer.TransferMoney;
import com.bionic.edu.transfer.TransferService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;

@Named
public class Hello {
    private static Logger logger = LogManager.getLogger();

    @Inject
    PayListService payListService;
    @Inject
    TransferService transferService;

    public static void main(String[] args) {
        /*ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
        Hello app = (Hello) context.getBean("hello");*/



    /*    LocalDate dt = LocalDate.now();
        LocalDate dt2 = LocalDate.now();
        *//*dt.plusWeeks(2);*//*
        logger.trace(dt2.isAfter(dt));
        logger.trace(dt2.compareTo(dt));*/
        /*app.doTransfer(12);*/
    }

    private static Optional<Long> getLong(){
        return Optional.ofNullable(getValue());
    }


    static Long getValue(){
        return 6L;
    }

    private void showUnpayed(){
        List<PayList> list = payListService.findAll();
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
