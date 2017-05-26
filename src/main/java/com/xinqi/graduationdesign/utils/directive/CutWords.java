package com.xinqi.graduationdesign.utils.directive;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: xinqi
 * Date: 2017/1/7
 * Time: 8:39
 */
public class CutWords implements TemplateMethodModel {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 截取字符串
     * @param arguments
     * @return
     * @throws TemplateModelException
     */
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        String name = arguments.get(0).toString();
        int cutIndex = Integer.parseInt(arguments.get(1).toString()) * 3;
        byte[] bytes;
        try {
            bytes = name.getBytes("utf-8");
        } catch (Exception e) {
            logger.error(e.toString());
            return name;
        }
        if(bytes.length > cutIndex){
            try {
                name = cutByteByU8(bytes,cutIndex);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.toString());
                return "异常错误";
            }
            return  name + "...";
        }else{
            return name;
        }
    }

    /**
     * 如果超过指定字节，则防止乱码并截取
     * @return
     * @throws Exception
     */
    private static String cutByteByU8(byte[] buf, int j) throws Exception {
        int count = 0;
        int i =0;
        for(i = j-1 ; i >= 0 ; i--){
            if(buf[i]<0)
                count++;
            else
                break;
        }
        String s =null;
        //因為UTF-8三個字節表示一個漢字
        if(count%3 == 0)
            s =new String(buf,0,j,"utf-8");
        else if(count%3 == 1)
            s= new String(buf,0,j-1,"utf-8");
        else if(count%3 == 2)
            s= new String(buf,0,j-2,"utf-8");
        return s;
    }
}
