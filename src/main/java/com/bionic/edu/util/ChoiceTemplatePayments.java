package com.bionic.edu.util;

import java.util.LinkedList;
import java.util.List;


public class ChoiceTemplatePayments implements ChoiceTemplate {

    private static List<String> resultHeaders = new LinkedList<>();

    private static List<String> buttonsShow = new LinkedList<>();

    private static List<String> buttonsAdd = new LinkedList<>();

    private static final String HEADER = "Payment";

    private static String info = "Select merchant from list above and click 'Show Single' " +
            "to view all the payments for only one merchant. Click 'Add New' if you want to " +
            "add a new payment. Choose 'Show All' if you want to view all the payments for every merchant.";

    private static ChoiceTemplate choiceTemplate;

    public static ChoiceTemplate getInstance(){
        return choiceTemplate == null ? choiceTemplate = new ChoiceTemplatePayments() : choiceTemplate;
    }

    static {
        buttonsShow.add("Show All");
        buttonsShow.add("Show Single");

        buttonsAdd.add("Add New");

        resultHeaders.add("Id");
        resultHeaders.add("Date");
        resultHeaders.add("mId");
        resultHeaders.add("cId");
        resultHeaders.add("Goods");
        resultHeaders.add("Sum");
        resultHeaders.add("Charge");
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