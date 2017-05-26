package com.xinqi.framework.upload;

import java.io.File;

/**
 * Created by 李坤 on 2016/12/12.
 */
public class UploadFile {
    /* 名称 */
    private String filename;

    /* 后缀名 */
    private String suffix;

    /* 上传文件目录 */
    private String dir;

    /* 上传名称 */
    private String original;

    /* 上传类型 */
    private String type;

    /* 文件字节数 */
    private long size;

    /* 文件上传码 */
    private UploadCode uploadCode = UploadCode.NORMAL;


    /**
     * 是否上传成功
     */
    public boolean isSuccess() {
        if ( UploadCode.NORMAL == getUploadCode() ) {
            return true;
        }
        return false;
    }


    /**
     * 文件路径
     */
    public String getFileUrl() {
        if ( dir == null || filename == null ) {
            return null;
        } else {
            return dir + File.separator + filename;
        }
    }


    /**
     * 上传文件
     */
    public File getFile() {
        if ( dir == null || filename == null ) {
            return null;
        } else {
            return new File(dir + File.separator + filename);
        }
    }


    /**
     * 删除上传文件
     */
    public boolean delFile() {
        File file = getFile();
        if ( file != null ) {
            return file.delete();
        }
        return false;
    }


    public String getContentType() {
        return type;
    }


    public String getFilesystemName() {
        return filename;
    }


    public String getOriginalFileName() {
        return original;
    }


    public String getFilename() {
        return filename;
    }


    public void setFilename( String filename ) {
        this.filename = filename;
    }


    public String getSuffix() {
        return suffix;
    }


    public void setSuffix( String suffix ) {
        this.suffix = suffix;
    }


    public String getDir() {
        return dir;
    }


    public void setDir( String dir ) {
        this.dir = dir;
    }


    public String getOriginal() {
        return original;
    }


    public void setOriginal( String original ) {
        this.original = original;
    }


    public String getType() {
        return type;
    }


    public void setType( String type ) {
        this.type = type;
    }


    public long getSize() {
        return size;
    }


    public void setSize( long size ) {
        this.size = size;
    }


    public UploadCode getUploadCode() {
        return uploadCode;
    }


    public void setUploadCode( UploadCode uploadCode ) {
        this.uploadCode = uploadCode;
    }

}
