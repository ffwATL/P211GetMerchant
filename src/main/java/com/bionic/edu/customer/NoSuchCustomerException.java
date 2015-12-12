package com.bionic.edu.customer;


import com.bionic.edu.GetMerchantException;

public class NoSuchCustomerException extends GetMerchantException {

    private String message = "No such customer";

    public NoSuchCustomerException(){}

    public NoSuchCustomerException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
