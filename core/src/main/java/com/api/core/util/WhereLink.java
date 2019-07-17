package com.api.core.util;


public enum WhereLink {
    LIKE(1, "%");

    private Integer code;
    private String sign;

    WhereLink(int i, String s) {
        this.code=i;
        this.sign=s;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
