package com.bionic.edu.merchant;


import com.bionic.edu.GetMerchantException;

public class NoSuchMerchantException extends GetMerchantException{

    private String message= "No such merchant exist";

    public NoSuchMerchantException(){}

    public NoSuchMerchantException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
