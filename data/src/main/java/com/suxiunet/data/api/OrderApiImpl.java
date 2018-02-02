package com.suxiunet.data.api;

import android.content.Context;

import com.suxiunet.data.entity.base.ApiResponse;
import com.suxiunet.data.entity.order.OrderInfoEntity;
import com.suxiunet.data.entity.order.OrderListEntity;
import com.suxiunet.data.entity.order.OrderSignEntity;
import com.suxiunet.data.factory.RetrofitFactory;

import java.util.List;

import rx.Observable;

/**
 * author : chenzhi
 * time   : 2018/01/19
 * desc   : 订单模块
 */
public class OrderApiImpl implements OrderApi {

    private final OrderApi mApi;

    public OrderApiImpl(Context context) {
        mApi = RetrofitFactory.creat(OrderApi.class, context);
    }

    /**
     * 
     * @param loginId
     * @param status
     * @param loginType
     * @return
     */
    @Override
    public Observable<ApiResponse<OrderListEntity>> getOrderList(String loginId, String status,String loginType) {
        return mApi.getOrderList(loginId,status,loginType);
    }

    /**
     * 
     * @param orderNo
     * @param total_amount
     * @param body
     * @param subject
     * @return
     */
    @Override
    public Observable<ApiResponse<OrderSignEntity>> getAliPayOrderInfo(String orderNo, String total_amount, String body, String subject) {
        return mApi.getAliPayOrderInfo(orderNo,total_amount,body,subject);
    }
}
