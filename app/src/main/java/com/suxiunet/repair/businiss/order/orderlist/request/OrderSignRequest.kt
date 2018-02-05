package com.suxiunet.repair.businiss.order.orderlist.request

import com.suxiunet.data.entity.base.BasicDataEntity
import com.suxiunet.repair.base.BasicRequest

/**
 * author : chenzhi
 * time   : 2018/01/25
 * desc   :
 */
class OrderSignRequest : BasicRequest() {
    /**
     * 登录id
     */
    var loginId = ""
    /**
     * 订单号
     */
    var orderNo = ""
    /**
     * 商品金额
     */
    var total_amount = ""

    var body = ""
    
    var subject = ""
}