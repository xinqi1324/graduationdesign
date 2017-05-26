package com.xinqi.graduationdesign.controller;

import com.xinqi.framework.upload.UploadHelper;
import com.xinqi.framework.upload.UploadMsg;
import com.xinqi.kisso.annotation.Action;
import com.xinqi.kisso.annotation.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 李坤 on 2016/12/10.
 */
@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController {

    @ResponseBody
    @Permission(action = Action.Skip)
    @RequestMapping(value = "/file")
    public String file(@RequestParam("file") CommonsMultipartFile file){
        UploadHelper uploadHelper = new UploadHelper(request,file,"product");
        UploadMsg msg = uploadHelper.upload();
        return toJson(msg);
    }
    /**
     * 上传规格图片
     * @param file
     * @return
     */
    @ResponseBody
    @Permission(action = Action.Skip)
    @RequestMapping(value = "/specificationSmallImage")
    public String specificationSmallImage(@RequestParam("file") CommonsMultipartFile file){
        UploadHelper uploadHelper = new UploadHelper(request,file,"specificationSmall");
        UploadMsg msg = uploadHelper.upload();
        return toJson(msg);
    }
    @ResponseBody
    @Permission(action = Action.Skip)
    @RequestMapping(value = "/uploadImg")
    public String uploadImg(@RequestParam("file") CommonsMultipartFile file,String folderName){
        UploadHelper uploadHelper = new UploadHelper(request,file,folderName);
        UploadMsg msg = uploadHelper.upload();
        return toJson(msg);
    }
    @ResponseBody
    @Permission(action = Action.Skip)
    @RequestMapping(value = "/temporaryImageDownload")
    public String temporaryImageDownload(@RequestParam("file") CommonsMultipartFile file){
        UploadHelper uploadHelper = new UploadHelper(request,file,"temporaryImage");
        UploadMsg msg = uploadHelper.upload();
        return toJson(msg);
    }
    @ResponseBody
    @Permission(action = Action.Skip)
    @RequestMapping(value = "layEdit")
    public String layEdit(@RequestParam("file") CommonsMultipartFile file){
        UploadHelper uploadHelper = new UploadHelper(request,file,"product/description",50*1024*1024);
        UploadMsg msg = uploadHelper.upload();
        Map<String,Object> result = new HashMap<String,Object>();
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("src",msg.getUrl());
        int code;
        if(msg.isSuccess())
            code = 0;
        else
            code = 1;
        result.put("code",code);
        result.put("msg",msg.getMsg());
        result.put("data",data);
        return toJson(result);
    }

    //TODO 大文件分片上传，配合WebUploader
    public String bigFile(){
        return "";
    }
}
