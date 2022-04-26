package com.example.schimbacrypto_marca.service;


import com.example.schimbacrypto_marca.components.Currency;
import com.example.schimbacrypto_marca.components.Display;
import com.example.schimbacrypto_marca.utils.UpdateCurrency;
import com.example.schimbacrypto_marca.utils.UpdateScreens;

public class AppHandlers{
    private Display display;

    public AppHandlers(Display display) {
        this.display = display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public void charButtonAction(char key, UpdateScreens updateScreens){
        String temp = display.getText_value();
        if(key == '.' && (temp.contains(".") || temp.length()==0)) return;
        temp += key;
        display.setText_value(temp);
        updateScreens.updateUpScreen(temp);
        Double temp_value = Double.valueOf(temp);
        display.setValue(temp_value);
        temp_value /= display.getUnit_value();
        updateScreens.updateDownScreen(String.valueOf(temp_value));
    }

    public void UndoButtonAction(UpdateScreens updateScreens){
        String temp = display.getText_value();
        if(temp.length()==0) return;
        temp = temp.substring(0, temp.length()-1);
        display.setText_value(temp);
        updateScreens.updateUpScreen(temp);
        Double temp_value = 0.0;
        if(temp.length()>0)
            temp_value = Double.valueOf(temp);
        display.setValue(temp_value);
        updateScreens.updateDownScreen(String.valueOf(temp_value/display.getUnit_value()));
    }

    public void ResetButtonAction(UpdateScreens updateScreens){
        display.setValue(display.getUnit_value());
        String temp = String.valueOf(display.getUnit_value());
        display.setText_value(temp);
        updateScreens.updateUpScreen(temp);
        updateScreens.updateDownScreen("1.0");
    }

    public void SwapButtonAction(UpdateScreens updateScreens){
        Double upValue = display.getValue();
        Double downValue = upValue / display.getUnit_value();
        display.setValue(downValue);
        display.setUnit_value(1.0/display.getUnit_value());
        String temp = String.valueOf(downValue);
        display.setText_value(temp);
        updateScreens.updateUpScreen(String.valueOf(temp));
        updateScreens.updateDownScreen(String.valueOf(upValue));
    }

    public void changeCurrency(Currency currency, UpdateCurrency updateCurrency){
        this.display = new Display(currency.getRapport_with_base_currency());
        updateCurrency.updateUpScreen(display.getText_value());
        updateCurrency.updateDownScreen("1.0");
        updateCurrency.updateLogo(currency);
    }

  

}
