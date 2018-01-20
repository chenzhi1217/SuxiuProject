package com.suxiunet.repair.businiss.order.orderlist.request

import com.suxiunet.repair.base.BasicRequest

/**
 * author : chenzhi
 * time   : 2018/01/19
 * desc   :
 */
class OrderListRequest : BasicRequest() {
    /**
     * 登陆id
     */
    var loginId = ""
    
    /**
     *  A("A","已下单"),
        B("B","已指派"),
        C("C","已维修"),
        D("D","订单完成"),
        E("E","订单已取消");
     */
    var status = ""
}