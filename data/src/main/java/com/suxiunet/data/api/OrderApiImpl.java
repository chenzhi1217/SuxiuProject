package com.suxiunet.data.api;

import android.content.Context;

import com.suxiunet.data.entity.base.ApiResponse;
import com.suxiunet.data.entity.order.OrderInfoEntity;
import com.suxiunet.data.entity.order.OrderListEntity;
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

    @Override
    public Observable<ApiResponse<OrderListEntity>> getOrderList(String loginId, String status) {
        return mApi.getOrderList(loginId,status);
    }
}
