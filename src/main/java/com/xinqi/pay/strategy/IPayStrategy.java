package com.xinqi.pay.strategy;

import com.xinqi.pay.PayType;

import java.util.Map;

/**
 * Created by 李坤 on 2016/12/15.
 */
public interface IPayStrategy {

    /**
     * 调用对应支付平台组装支付请求报文
     * @param payType 传入需要的支付方式
     * @param params  其他额外需要的参数
     * @return 生成的支付请求
     */
    String generatePayParams(PayType payType,Map<String,Object> params);
}
