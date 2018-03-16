package com.suxiunet.repair.businiss.home.presenter

import android.app.Activity
import android.text.TextUtils
import com.suxiunet.data.exception.ApiException
import com.suxiunet.data.util.SpUtil
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.base.BasicProxy
import com.suxiunet.repair.businiss.center.proxy.PlaceOrderProxy
import com.suxiunet.repair.businiss.center.request.OrderInfoRequest
import com.suxiunet.repair.businiss.home.contract.PlaceOrderContract
import com.suxiunet.repair.plugin.AliMapCode
import com.suxiunet.repair.util.ToastUtil

/**
 * author : chenzhi
 * time   : 2018/01/14
 * desc   : 一键下单
 */
class PlaceOrderPresenter : BasicPresenter<PlaceOrderContract.View, PlaceOrderContract.Model> {

    var mProxy: PlaceOrderProxy
    var mRequest: OrderInfoRequest
    constructor(activity: Activity, view: PlaceOrderContract.View, model: PlaceOrderContract.Model):super(activity, view, model){
        mProxy = PlaceOrderProxy(mActivity)
        mRequest = OrderInfoRequest()
    }
    
    /**
     * 开启定位功能
     */
    fun startLocation() {
        AliMapCode.setOnceLocation()
        AliMapCode.startLocation(mActivity,object : AliMapCode.LocationChangeListener{
            override fun locationDataSuccess(latitude: String?, longitude: String?, province: String?, city: String?, district: String?, street: String?) {
                mView.locationSuccess(province + " " + city + " " + district,street)
            }

            override fun locationDataFail(errorCode: Int, errorInfo: String?) {
                mView.locationError(errorInfo)
            }
        })
    }

    /**
     * 产生订单
     */
    fun placeOrder(company: String, name: String, phone: String, time: String, style: String, machineType: String, equModel: String, addrs: String, street: String, desc: String, invoiceHead: String) {
        if (!checkData(name, phone, time, machineType,equModel, addrs, desc)) {
            return
        }
        mProxy.setCallBack(object : BasicProxy.ProxyCallBack<OrderInfoRequest,Any>{
            override fun onLoadSuccess(req: OrderInfoRequest?, type: BasicProxy.ProxyType, data: Any?) {
                mView.placeOrderSuccess()
            }

            override fun onLoadError(req: OrderInfoRequest?, type: BasicProxy.ProxyType, e: ApiException?) {
                mView.placeOrderError(e)
            }
        })
        val loginId = SpUtil.getString(mActivity, SpUtil.LOGIN_ID_KEY, "")
        mRequest.loginId = loginId
        mRequest.contacts = name//姓名
        mRequest.company = company//公司名称
        mRequest.contactTel = phone//联系人号码
        mRequest.appointmentTime = time//预约时间
        mRequest.serviceMode = style//服务方式
        mRequest.machineMode = machineType//设备类型
        mRequest.machineType = equModel//机器型号
        mRequest.companyAdr = addrs + street//地址信息
        mRequest.desc = desc
        mRequest.isNeedInvoice = if(invoiceHead.isNullOrEmpty()) "1" else "0"
        mRequest.invoiceHead = invoiceHead
        
        mProxy.request(mRequest,BasicProxy.ProxyType.REFRESH_DATA)
        
    }

    /**
     * 判断数据的准确性
     */
    fun checkData(name: String, phone: String, time: String,machineType: String,equModel: String, addrs: String, desc: String): Boolean {
        if (TextUtils.isEmpty(name)) {
            ToastUtil.showToast("请输入姓名")
            return false
        }else if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast("请输入手机号")
            return false
        }else if (TextUtils.isEmpty(time)) {
            ToastUtil.showToast("请输入预约时间")
            return false
        }else if (TextUtils.isEmpty(machineType)) {
            ToastUtil.showToast("请输入设备类型")
            return false
        } else if (TextUtils.isEmpty(equModel)) {
            ToastUtil.showToast("请输入设备型号")
            return false
        } else if (TextUtils.isEmpty(addrs)) {
            ToastUtil.showToast("请输入地址信息")
            return false
        } else if (TextUtils.isEmpty(desc)) {
            ToastUtil.showToast("请输入故障描述")
            return false
        }
        return true
    }
}