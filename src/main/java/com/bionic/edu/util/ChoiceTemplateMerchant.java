package com.bionic.edu.util;


import java.util.LinkedList;
import java.util.List;

public class ChoiceTemplateMerchant implements ChoiceTemplate {

    private static List<String> resultHeaders = new LinkedList<>();

    private static List<String> buttonsShow = new LinkedList<>();

    private static List<String> buttonsAdd = new LinkedList<>();

    private static String info ="Nothing interesting is here. But you can imagine something great right now in that place";

    private static final String HEADER = "Merchant";
    private static ChoiceTemplateMerchant choiceTemplateMerchant;
    private ChoiceTemplateMerchant(){}

    public static ChoiceTemplate getInstance(){
        return choiceTemplateMerchant == null ? choiceTemplateMerchant = new ChoiceTemplateMerchant() : choiceTemplateMerchant;
    }

    static {
        buttonsShow.add("Show All");
        buttonsShow.add("Home");

        resultHeaders.add("Id");
        resultHeaders.add("Name");
        resultHeaders.add("Period");
        resultHeaders.add("Min sum");
        resultHeaders.add("Need to send");
        resultHeaders.add("Charge");
        resultHeaders.add("Sent");
        resultHeaders.add("Last Sent");
    }

    @Override
    public List<String> getButtonsShow() {
        return buttonsShow;
    }

    @Override
    public List<String> getButtonsAdd() {
        return buttonsAdd;
    }

    @Override
    public String getInfoText() {
        return info;
    }

    @Override
    public String getHeader() {
        return HEADER;
    }

    @Override
    public List<String> getResultHeaders() {
        return resultHeaders;
    }
}
