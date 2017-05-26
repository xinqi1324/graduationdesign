package com.xinqi.graduationdesign.utils;

import com.xinqi.framework.file.FileUtil;
import org.apache.commons.lang.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: xinqi
 * Date: 2017/1/9
 * Time: 15:45
 */
public class ServiceUtils {

    /**
     * 集合去重
     * @param list
     * @return
     */
    public static <T> List<T> ArrayRepeatDelete(List<T> list) {
        //将集合转为不可重复的set
        HashSet<T> set = new HashSet<T>(list);
        //再转回来
        list = new ArrayList<T>(set);
        return list;
    }

    /**
     * 计算总页数
     * @param allCount  总数量
     * @param pageCount 每页的个数
     * @return
     */
    public static int calculationPageSize(int allCount, int pageCount) {
        int pageSize;
        //计算总页数
        if (allCount % pageCount == 0) {
            pageSize = allCount / pageCount;
        } else if (allCount > pageCount) {
            pageSize = allCount / pageCount + 1;
        } else {
            pageSize = 1;
        }
        return pageSize;
    }

    /**
     * 判断数组中是否有重复元素
     * @param strArr
     * @return
     */
    public static boolean arrayIsRepeat(String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            for (int j = 0; j < strArr.length; j++) {
                if (j == i) {
                    continue;
                } else {
                    if (strArr[i].equals(strArr[j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static String getMD5(String para) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 生成一个MD5加密计算摘要
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(para.getBytes());
        byte b[] = md5.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        String result = buf.toString();
        return result;
    }

    /**
     * 将MD5集合排序并拼接成字符串
     * @param list
     * @return
     */
    public static String sortMD5Array(List<String> list) {
        StringBuffer sb = new StringBuffer();
        if (list != null) {
            Collections.sort(list);
            for (int i = 0; i < list.size(); i++) {
                if (i != list.size() - 1) {
                    sb.append(list.get(i) + ",");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

    /**
     *
     * @param imageArr 图片数组
     * @param newPath 新相对路径
     * @param realPath 绝对路径（realPath）
     * @return
     * @throws IOException
     */
    public static String dumpImage(String[] imageArr,String newPath,String realPath) throws IOException{
        //TODO 为商品、sku、品牌等内容添加这个方法
        List<String> imageList = new ArrayList<String>();
        realPath = realPath.substring(0,realPath.length() - 1);
        realPath = realPath.replaceAll("\\\\", "/");
        for(String image:imageArr){
            //原来的绝对路径
            String oldRealPath = realPath + image;
            File file = new File(oldRealPath);
            BufferedImage fileWrite = ImageIO.read(file);
            //新的绝对路径
            String newRealPath = realPath + newPath + file.getName();
            if (ImageIO.write(fileWrite, "jpg", new File(newRealPath))) {
                imageList.add(newPath + file.getName());
                //删除原文件
                FileUtil.removeFile(oldRealPath);
            }
        }
        return StringUtils.join(imageList,",");
    }
}
