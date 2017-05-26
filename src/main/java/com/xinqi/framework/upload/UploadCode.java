package com.xinqi.framework.upload;

import com.xinqi.framework.common.IEnum;

/**
 * Created by 李坤 on 2016/12/12.
 */
public enum  UploadCode implements IEnum {
    NORMAL(0, "上传成功."), ILLEGAL_EXT(1, "非法扩展名."), EXCEPTION(3, "上传异常.");

    private final int key;

    private final String desc;


    private UploadCode( int key, String desc ) {
        this.key = key;
        this.desc = desc;
    }


    @Override
    public int key() {
        return key;
    }


    @Override
    public String desc() {
        return desc;
    }
}
