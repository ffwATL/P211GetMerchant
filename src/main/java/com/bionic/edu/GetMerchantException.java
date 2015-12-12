package com.bionic.edu;


public class GetMerchantException extends Exception {

    private String message = "Get merchant exception";


    public GetMerchantException(){}

    public GetMerchantException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
