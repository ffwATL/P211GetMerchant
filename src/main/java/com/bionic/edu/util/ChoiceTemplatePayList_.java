package com.bionic.edu.util;

import java.util.LinkedList;
import java.util.List;


public class ChoiceTemplatePayList_ implements ChoiceTemplate {

    private static List<String> resultHeaders = new LinkedList<>();

    private static List<String> buttonsShow = new LinkedList<>();

    private static List<String> buttonsAdd = new LinkedList<>();

    private static String info ="Select merchant from list above and click 'Show Single' to view pay list " +
            "for only one merchant. Chose 'Show All' if you want to view pay list for every merchant.Click 'Create Single' " +
            "if you want to create a new pay list for chosen merchant. " +
            "Click 'Create All' and it will create pay lists for all the merchants";

    private static final String HEADER = "Pay List";

    private static ChoiceTemplate choiceTemplate;

    static {
        buttonsShow.add("Show All");
        buttonsShow.add("Show Single");

        buttonsAdd.add("Create Single");
        buttonsAdd.add("Create All");

        resultHeaders.add("Id");
        resultHeaders.add("Merchant Id");
        resultHeaders.add("Period");
        resultHeaders.add("Minimal sum");
        resultHeaders.add("Need to send");
        resultHeaders.add("Date");
    }

    private ChoiceTemplatePayList_(){}

    public static ChoiceTemplate getInstance(){
        return choiceTemplate == null ? choiceTemplate = new ChoiceTemplatePayList_() : choiceTemplate;
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