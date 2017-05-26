package com.xinqi.framework.upload;

/**
 * Created by 李坤 on 2016/12/12.
 */
public class UploadMsg {
    /**
     * 是否成功
     */
    private boolean success = false;

    /**
     * 文件字节数
     */
    private long size;

    /**
     * 文件URL
     */
    private String url;

    /**
     * 提示信息
     */
    private String msg = "上传失败!";

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
