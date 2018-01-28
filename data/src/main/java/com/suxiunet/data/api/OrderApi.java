package com.suxiunet.data.api;

import com.suxiunet.data.entity.base.ApiResponse;
import com.suxiunet.data.entity.order.OrderInfoEntity;
import com.suxiunet.data.entity.order.OrderListEntity;
import com.suxiunet.data.entity.order.OrderSignEntity;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * author : chenzhi
 * time   : 2018/01/19
 * desc   : 订单模块接口
 */
public interface OrderApi {

    /**
     * 查询订单列表
     * @param loginId
     * @param status
     * @return
     */
    @FormUrlEncoded
    @POST("dnwx/app/order/queryOrderList")
    Observable<ApiResponse<OrderListEntity>> getOrderList(@Field("userId") String loginId, @Field("status") String status);

    /**
     * 获取验签
     * @return
     */
    @FormUrlEncoded
    @POST("dnwx/app/pay/alipay")
    Observable<ApiResponse<OrderSignEntity>> getAliPayOrderInfo(@Field("orderNo") String orderNo, @Field("total_amount") String total_amount, @Field("body") String body, @Field("subject") String subject);
}
