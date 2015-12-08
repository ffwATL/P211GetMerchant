package com.bionic.edu.merchant;

import com.bionic.edu.customer.CustomerService;
import com.bionic.edu.payment.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class AppMerchant {
    @Inject
    MerchantService merchantService;
    @Inject
    CustomerService customerService;
    @Inject
    PaymentService paymentService;

    static Logger logger = LogManager.getLogger();
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-config.xml");
        AppMerchant app = (AppMerchant)context.getBean("appMerchant");
        app.findAll();
    }

    private void findAll(){
        List<Merchant> list = merchantService.findAll();
        String f = "";
        logger.trace(String.format("id %-1s Name %-21s Bank Name %-6s Account %-4s Charge %s Min sum", f, f, f, f, f));
        logger.trace("----------------------------------------------------------------------------");
        for(Merchant m: list){
            logger.trace(String.format("%-4d %-26s %-16s %-12s %-7.3f %.2f", m.getId(), m.getName(), m.getBankName(), m.getAccount(),
                    m.getCharge(), m.getMinSum()));
        }
    }


}