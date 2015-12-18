package com.bionic.edu.merchant.paylist;


import com.bionic.edu.merchant.Merchant;
import com.bionic.edu.merchant.MerchantService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class PayListApp {
    @Inject
    PayListService payListService;
    @Inject
    MerchantService merchantService;

    private static Logger logger = LogManager.getLogger();

    /*public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
        PayListApp app = (PayListApp) context.getBean("payListApp");
        app.add(12);
        app.showAll();

    }*/

    /*private void showAll(){
        List<PayList> list = payListService.findAll();
        for(PayList p: list){
            logger.trace(p.getId()+" " + p.getMerchantId()+ " " + p.getPeriod()+" "+ p.getMinSum());
        }
    }*/

    public void add(int merchantId){
        Merchant m = merchantService.findById(merchantId);
        add(m);
    }

    public void add(Merchant m){
        if(m != null && m.getNeedToSend() >= m.getMinSum()){
            payListService.addPayList(m);
            merchantService.resetNeedToSend(m);
        }
    }

    public List<PayList> updateAll(){
        List<Merchant> list = merchantService.findAll();
        for(Merchant m: list){
            add(m);
        }
        return findAll();
    }

    public List<PayList> findAll(){
        return payListService.findAll();
    }

    public List<PayList> findByMerchantId(int id){
        return payListService.findByMerchantId(id);
    }

}