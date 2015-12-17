package com.bionic.edu.util;


import java.util.List;

public interface ChoiceTemplate {

    List<String> getButtonsShow();

    List<String> getButtonsAdd();

    String getInfoText();

    String getHeader();

    List<String> getResultHeaders();
}
