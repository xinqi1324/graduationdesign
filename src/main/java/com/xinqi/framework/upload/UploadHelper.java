package com.xinqi.framework.upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by 李坤 on 2016/12/12.
 */
public class UploadHelper {
    protected Logger logger = LoggerFactory.getLogger(UploadHelper.class);

    private static final int DEFAULT_MAX_POST_SIZE = 1024 * 1024; // 1M，文件默认最大大小为1M

    private static final String ROOT_UPLOAD_DIR = "upload";

    private HttpServletRequest request;

    private MultipartFile file;

    private String saveDirectory; //文件保存目录

    private int maxPostSize; //文件最大字节数

    private UploadFile uploadFile; //上传文件信息

    private FileRenamePolicy fileRenamePolicy = new UUIDFileRenamePolicy(); //默认为UUID命名策略

    public UploadHelper(HttpServletRequest request, MultipartFile file,String saveDirectory){
        this(request,file,saveDirectory,DEFAULT_MAX_POST_SIZE);
    }

    public UploadHelper( HttpServletRequest request, MultipartFile file, String saveDirectory, int maxPostSize ) {
        this.request = request;
        this.file = file;
        this.saveDirectory = saveDirectory;
        this.maxPostSize = maxPostSize;
    }

    public UploadMsg upload(){
        if ( request == null ) {
            throw new IllegalArgumentException("request不能为空");
        }
        if ( saveDirectory == null ) {
            throw new IllegalArgumentException("文件保存目录不能为空！");
        }
        if ( maxPostSize <= 0 ) {
            throw new IllegalArgumentException("最大文件大小不能小于零！");
        }

        UploadMsg msg = new UploadMsg();
        //上传文件大小判断
        if(file.getSize() > this.maxPostSize){
            msg.setMsg("文件大小超出限制！");
            return msg;
        }

        //上传原始文件名
        String originalName = file.getOriginalFilename();
        //上传目标文件
        File targetFile = new File(getRootPath() + File.separator + saveDirectory,originalName);
        targetFile = fileRenamePolicy.rename(targetFile);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
            //设置上传返回信息
            msg.setMsg("上传成功！");
            msg.setSuccess(true);
            msg.setSize(file.getSize());
            msg.setUrl(request.getContextPath() + "/" + ROOT_UPLOAD_DIR + "/" + saveDirectory + "/" + targetFile.getName());

            //设置上传文件信息
            uploadFile = new UploadFile();
            uploadFile.setFilename(targetFile.getName());
            uploadFile.setSize(file.getSize());
            uploadFile.setDir(targetFile.getAbsolutePath());
            uploadFile.setOriginal(originalName);
            uploadFile.setType(file.getContentType());

        } catch (IOException e) {
            msg.setMsg(e.getMessage());
        }
        return msg;
    }

    /**
     * 调用upload()方法后调用，获取上传文件信息
     * @return
     */
    public UploadFile getFileInfo(){
        return uploadFile;
    }

    private String getRootPath(){
        return request.getSession().getServletContext().getRealPath(ROOT_UPLOAD_DIR);
    }

    /**
     * 设置命名策略
     * @param fileRenamePolicy
     */
    public void setFileRenamePolicy(FileRenamePolicy fileRenamePolicy) {
        this.fileRenamePolicy = fileRenamePolicy;
    }
}
