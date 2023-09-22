package com.ziko.keys;

import java.io.Serializable;

public class ProductKey implements Serializable {

    private String code;
    private long number;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
}
