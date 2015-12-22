package com.bionic.edu.util;


import java.util.LinkedList;
import java.util.List;

public class ChoiceTemplateTransfer implements ChoiceTemplate{

    private static List<String> resultHeaders = new LinkedList<>();

    private static List<String> buttonsShow = new LinkedList<>();

    private static List<String> buttonsAdd = new LinkedList<>();

    private static final String info = "Choose 'Show All' to see all the paylists were proceeded for all the merchants." +
            " 'Show Single' shows you all the same but only for merchant you choose from list above. To make a new transfer due to the " +
            "terms and conditions click on 'New Transfer'";

    private static final String HEADER = "Transfer Money";

    private static ChoiceTemplate choiceTemplate;

    static {
        buttonsShow.add("Show All");
        buttonsShow.add("Show Single");

        buttonsAdd.add("New Transfer");

        resultHeaders.add("ID");
        resultHeaders.add("Merchant ID");
        resultHeaders.add("Pay list ID");
        resultHeaders.add("Sum");
        resultHeaders.add("Date");
    }

    private ChoiceTemplateTransfer(){}

    public static ChoiceTemplate getInstance(){
        return choiceTemplate == null ? choiceTemplate = new ChoiceTemplateTransfer() : choiceTemplate;
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