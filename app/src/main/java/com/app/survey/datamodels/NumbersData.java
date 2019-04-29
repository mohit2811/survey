package com.app.survey.datamodels;

/**
 * Created by ghumman on 9/12/2017.
 */

public class NumbersData  {

    private int number ;

    private Boolean isSelected = false ;

    public void setNumber(int number) {
        this.number = number;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public int getNumber() {
        return number;
    }
}
