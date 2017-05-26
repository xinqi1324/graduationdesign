package com.xinqi.graduationdesign.common.enums;

import com.xinqi.framework.common.IEnum;

/**
 * Created by ASUS on 2017/5/13.
 */
//暂时将税务简化为 无论薪资，全部要缴纳10%的税务
public enum TaxType {
    TAX(1, "百分之10的税率", 0.1);

    private final int key;
    private final String desc;
    private final double tax;

    TaxType(final int key, final String desc, double tax) {
        this.key = key;
        this.desc = desc;
        this.tax = tax;
    }

    public int key() {
        return this.key;
    }

    public String desc() {
        return this.desc;
    }

    public double tax() {
        return this.tax;
    }
}