package com.xinqi.graduationdesign.common.enums;

import com.xinqi.framework.common.IEnum;

/**
 * Created by ASUS on 2017/5/12.
 */
public enum LeaveStatusType implements IEnum {
    APPLYING(0, "申请中"),
    AGREED(1, "已同意"),
    DISAGREED(2,"已拒绝"),
    CANCELED(3,"已取消");

    private final int key;
    private final String desc;

    LeaveStatusType(final int key, final String desc) {
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
