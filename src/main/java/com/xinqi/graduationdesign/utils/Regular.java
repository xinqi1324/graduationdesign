package com.xinqi.graduationdesign.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: xinqi
 * Date: 2016/12/17
 * Time: 14:50
 */
public class Regular {
    /**
     * 截取图片标签中的路径
     * @param img
     * @return
     */
    public static List<String> getImgSrcs(String img){
        List<String> list = new ArrayList<String>();
        String reg = "<img[^<>]*?\\ssrc=['\"]?(.*?)['\"]?\\s.*?>";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(img);
        while(matcher.find()) {
            list.add(matcher.group(1));
        }
       return list;
    }
}
