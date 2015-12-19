package com.bionic.edu;


import com.bionic.edu.merchant.paylist.PayList;
import com.bionic.edu.merchant.paylist.PayListService;
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

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
        Hello app = (Hello) context.getBean("hello");
        app.showUnpayed();
    }

    private void showUnpayed(){
        List<PayList> list = payListService.findUnPayed();
        for(PayList p : list){
            logger.trace("need to send: " + p.getNeedToSend());
        }
    }


}
