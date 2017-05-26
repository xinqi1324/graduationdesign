package com.xinqi.pay.strategy;

import com.xinqi.pay.PayType;

import java.util.Map;

/**
 * 支付宝Web支付 （对接即时到账支付，包含国内、国际）
 * Created by 李坤 on 2016/12/15.
 */
public class AlipayWebStrategy implements IPayStrategy{
    @Override
    public String generatePayParams(PayType payType, Map<String, Object> params) {
        return null;
    }

    private String makeMainParam(Map<String,Object> params,String retUrl){
        return null;
    }
}
