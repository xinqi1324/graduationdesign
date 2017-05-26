package com.xinqi.graduationdesign.common.enums;

import com.xinqi.framework.common.IEnum;

/**
 * Created by ASUS on 2017/5/11.
 */
public enum  EducationType implements IEnum{
    BEFORE_COLLEGE(0, "专科以下"),

    COLLEGE(1, "专科毕业"),

    UNDERGRADUATE(2, "本科毕业"),

    GRADUATE(3, "硕士毕业"),

    DOCTOR(4, "博士毕业");

    private final int key;
    private final String desc;

    EducationType(final int key, final String desc) {
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
