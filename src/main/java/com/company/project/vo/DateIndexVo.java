package com.company.project.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DateIndexVo {

    List<String> dateList = new ArrayList<>();

    //List<Map<String, Long>> numberList = new ArrayList<Map<String, Long>>();

    List<BigDecimal> numberList = new ArrayList<BigDecimal>();

    public List<String> getDateList() {
        return dateList;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

    public List<BigDecimal> getNumberList() {
        return numberList;
    }

    public void setNumberList(List<BigDecimal> numberList) {
        this.numberList = numberList;
    }
}
