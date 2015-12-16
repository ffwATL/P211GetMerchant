package com.bionic.edu.payment;


import com.bionic.edu.GetMerchantException;
import com.bionic.edu.merchant.Merchant;
import com.bionic.edu.merchant.MerchantService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Timestamp;
import java.util.List;

@Named
public class AppPayment {

    @Inject
    PaymentService paymentService;
    @Inject
    MerchantService merchantService;


    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
        AppPayment app = (AppPayment) context.getBean("appPayment");
       /* app.save(32, 2, "tester", 999.0, 9.0);*/
        /*app.getPaymentSum();*/
        app.save(2,2,"second merchant goods", 150.0);
        /*app.findAll();*/
    }

    private Double getPaymentSum(){
        Double d = paymentService.getPaymentSum();
        logger.trace("Payment sum: " + d);
        return d;
    }

    private void findAll(){
        List<Payment> list = paymentService.findAll();
        logger.trace(String.format("%-6s | %-6s | %-6s | %-37s | %-10s | %-6s | %-23s |","Id","Merchant","Customer","Goods","Payed",
                "Charged", "Date"));
        logger.trace("--------------------------------------------------------------" +
                "---------------------------------------------------------");
        for (Payment p: list){
            logger.trace(String.format("%-6d | %-8d | %-8d | %-37s | %-10.2f | %-7.2f | %-23s |", p.getId(), p.getMerchantId(), p.getCustomerId(),
                    p.getGoods(), p.getSumPayed(), p.getChargePayed(), p.getDt()));
        }
    }

    private void save(int merchantId, int customerId, String goods, double sumPayed) {
        Merchant m = merchantService.findById(merchantId);
        if(m != null){
            Payment p = new Payment();
            p.setCustomerId(customerId);
            p.setMerchant(m);
            p.setGoods(goods);
            p.setSumPayed(sumPayed);
            p.setDt(new Timestamp(System.currentTimeMillis()));
            p.setChargePayed(m.getCharge());
            try {
                paymentService.save(p);
                merchantService.updateNeedToSend(merchantId, sumPayed);
            } catch (GetMerchantException e) {
                logger.error(e.getMessage());
            }
        }
    }
}