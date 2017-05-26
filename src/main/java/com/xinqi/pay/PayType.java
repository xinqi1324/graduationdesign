package com.xinqi.pay;

/**
 * Created by 李坤 on 2016/12/15.
 */
public enum PayType {

    ALIPAY_WEB(1, "支付宝网页支付"),
    ALIPAY_WAP(2, "支付宝手机网页支付"),
    UNION_WEB(3, "银联网页支付"),
    UNION_WAP(4, "银联手机网页支付"),
    UNION_APP(5, "银联手机APP支付"),
    ALIPAY_APP(6, "支付宝手机APP支付");



    private Integer value;
    private String desc;
    private String name;

    PayType(Integer value,String desc){
        this.value = value;
        this.desc = desc;
    }

    public static PayType valueOf(int value){
        for (PayType type : PayType.values()) {
            if (type.value() == value) {
                return type;
            }
        }
        return null;
    }

    public Integer value() {
        return value;
    }

    public String desc() {
        return desc;
    }
}
