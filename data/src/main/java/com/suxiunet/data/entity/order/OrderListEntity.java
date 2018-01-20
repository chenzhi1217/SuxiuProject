package com.suxiunet.data.entity.order;

import com.suxiunet.data.entity.base.BasicDataEntity;

import java.util.List;

/**
 * author : chenzhi
 * time   : 2018/01/19
 * desc   : 
 */
public class OrderListEntity extends BasicDataEntity {
    private List<OrderInfoEntity> orderInfolist;

    public List<OrderInfoEntity> getOrderInfolist() {
        return orderInfolist;
    }

    public void setOrderInfolist(List<OrderInfoEntity> orderInfolist) {
        this.orderInfolist = orderInfolist;
    }
}
