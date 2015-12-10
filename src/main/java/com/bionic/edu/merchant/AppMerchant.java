package com.bionic.edu.merchant;

import com.bionic.edu.customer.CustomerService;
import com.bionic.edu.payment.Payment;
import com.bionic.edu.payment.PaymentService;
import com.bionic.edu.result.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class AppMerchant {
    @Inject
    MerchantService merchantService;
    @Inject
    CustomerService customerService;
    @Inject
    PaymentService paymentService;

    private static Logger logger = LogManager.getLogger();
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
        AppMerchant app = (AppMerchant)context.getBean("appMerchant");
        app.getAllPayments();
    }

    private void getAllPayments(){
        List<Result> hardList = new ArrayList<>();
        for (Merchant m: merchantService.getSortedByNeedToPay()){
           hardList.add(new Result(m.getName(), m.getPayments()));
        }
        for (Result r: hardList){
            logger.trace(r.getName());
            for(Payment p: r.getPayments()){
                logger.trace(p.getGoods()+ " " + p.getSumPayed());
            }
        }
    }

    private void getSortedByNeedToPay(){
        List<Merchant> list = merchantService.getSortedByNeedToPay();
        logger.trace(String.format("%-25s | %-12s |","Name","Need to send"));
        logger.trace("------------------------------------------");
        for(Merchant m: list){
            logger.trace(String.format("%-25s | %-12f |", m.getName(), m.getNeedToSend()));
        }
    }

    private void findAll(){
        List<Merchant> list = merchantService.findAll();
        String f = "";
        logger.trace(String.format("%-4s | %-26s | %-16s | %-12s | %-6s | %s |", "Id", "Name", "Bank Name", "Account", "Charge","Min sum"));
        logger.trace("-----------------------------------------------------------------------------------------");
        for(Merchant m: list){
            logger.trace(String.format("%-4d | %-26s | %-16s | %-12s | %-6.3f | %.2f |", m.getId(), m.getName(), m.getBankName(),
                    m.getAccount(), m.getCharge(), m.getMinSum()));
        }
    }

    private void getTotalReport(){
        List<Result> list = merchantService.getTotalReport();
        logger.trace(String.format("%-25s | %-12s |","Name","Sum"));
        logger.trace("------------------------------------");
        for(Result r: list){
            logger.trace(String.format("%-25s | %-6.3f |",r.getName(),r.getSum()));
        }
    }

}