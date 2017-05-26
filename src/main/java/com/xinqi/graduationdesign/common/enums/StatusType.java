package com.xinqi.graduationdesign.common.enums;

import com.xinqi.framework.common.IEnum;

/**
 * Created by ASUS on 2017/5/8.
 */
public enum StatusType implements IEnum {
    NORMAL(0, "正常"),

    FROZEN(1, "冻结"),

    DELETED(2, "已删除");

    private final int key;
    private final String desc;

    StatusType(final int key, final String desc) {
        this.key = key;
        this.desc = desc;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public String desc() {
        return this.desc;
    }
}
