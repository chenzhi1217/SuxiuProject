package com.suxiunet.repair.businiss.center.request

import com.suxiunet.repair.base.BasicRequest

/**
 * author : chenzhi
 * time   : 2018/01/18
 * desc   : 订单信息类
 */
class OrderInfoRequest : BasicRequest() {
    /**
     * 联系人
     */
    var contacts = ""
    /**
     * 联系人号码
     */
    var contactTel = ""
    /**
     * 预约时间
     */
    var appointmentTime = ""
    /**
     * 服务方式
     */
    var serviceMode = ""
    /**
     * 机器型号
     */
    var machineType = ""
    /**
     * 地址
     */
    var companyAdr = ""
}