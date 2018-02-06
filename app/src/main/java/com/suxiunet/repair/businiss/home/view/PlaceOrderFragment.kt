package com.suxiunet.repair.businiss.home.view

import android.app.Dialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.DatePicker
import android.widget.RadioGroup
import com.suxiunet.data.exception.ApiException
import com.suxiunet.data.util.SpUtil
import com.suxiunet.data.util.Utils
import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.NomalFragment
import com.suxiunet.repair.businiss.home.contract.PlaceOrderContract
import com.suxiunet.repair.businiss.home.model.PlaceOrderModel
import com.suxiunet.repair.businiss.home.presenter.PlaceOrderPresenter
import com.suxiunet.repair.businiss.order.orderlist.view.OrderActivity
import com.suxiunet.repair.databinding.DialogRepairStyleBinding
import com.suxiunet.repair.databinding.DialogTimeBinding
import com.suxiunet.repair.databinding.FragPlaceOrderBinding
import com.suxiunet.repair.util.DialogUtils
import com.suxiunet.repair.util.ToastUtil
import com.tbruyelle.rxpermissions.Permission
import com.tbruyelle.rxpermissions.RxPermissions
import rx.Subscriber
import java.util.*

/**
 * author : chenzhi
 * time   : 2018/01/14
 * desc   : 一键下单页面
 */
class PlaceOrderFragment: NomalFragment<PlaceOrderPresenter,FragPlaceOrderBinding>(),PlaceOrderContract.View, View.OnClickListener {
    
    var mServiceType = "A" //A: 上门  B：送修 C：其它
    //地图图标的旋转动画
    lateinit var mAnima: Animation

    var isCoarseGranted = false
    var isFineGranted = false
    
    //机器类型的参数
    lateinit var mEquipTypeBinding: DialogRepairStyleBinding
    var mEquipTypeDialog: Dialog? = null
    var mEquipType = ""//记录当前的设备类型
    //预约时间选择的参数
    lateinit var mTimeBinding: DialogTimeBinding
    var mTimeDialog: Dialog?= null

    /**
     * 订单提交成功成功
     */
    override fun placeOrderSuccess() {
        ToastUtil.showToast("下单成功")
        var intent = Intent(activity, OrderActivity::class.java)
        intent.putExtra("title","待付款订单")
        intent.putExtra("type","A")
        activity.startActivity(intent)
        activity.finish()
    }

    /**
     * 定位失败
     */
    override fun placeOrderError(e: ApiException?) {
        ToastUtil.showToast(e?.displayMessage?:"下单失败")
    }
    
    /**
     * 定位成功的回调方法
     */
    override fun locationSuccess(addr: String, street: String?) {
        stopAnima()
        mBinding.includeAddrInfo.etPlaceOrderAddr.setText(addr?:"")
        mBinding.includeAddrInfo.etPlaceOrderStreet.setText(street?:"")
        Utils.setCursorLocationEnd(mBinding.includeAddrInfo.etPlaceOrderAddr)
        Utils.setCursorLocationEnd(mBinding.includeAddrInfo.etPlaceOrderStreet)
    }

    /**
     * 定位失败的回调方法
     */
    override fun locationError(errInfo: String?) {
        stopAnima()
        ToastUtil.showToast(errInfo?:"定位失败")
    }

    var mYear: Int = 0
    var mMonth: Int = 0
    var mDay: Int = 0

    override fun initView() {
        //初始化动画
        mAnima = AnimationUtils.loadAnimation(context,R.anim.rotate_anim)
        //检测定位权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission()
        } else {
            initLocation()
        }
        //点击下单按钮
        mBinding.btPlaceOrder.setOnClickListener(this)
        //重新定位
        mBinding.includeAddrInfo.llLocationAgain.setOnClickListener(this)
        //初始化号码
        val phone = SpUtil.getString(context, SpUtil.LOGIN_ID_KEY, "")
        mBinding.includeBasicInfo.etPlaceOrderPhone.setText(phone)
        //设置服务方式选中监听
        mBinding.includeBasicInfo.rgRepairStyle.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                if (checkedId == R.id.rb_repair_sm) {
                    mServiceType = "A"
                } else {
                    mServiceType = "B"
                }
            }
        })
        //初始化设备类型的Dialog
        mEquipTypeBinding = DataBindingUtil.inflate<DialogRepairStyleBinding>(LayoutInflater.from(context), R.layout.dialog_repair_style, null, false)
        mEquipTypeBinding.fragment = this
        mEquipTypeDialog = DialogUtils.getInstance().setBottomDialog(activity, mEquipTypeBinding?.root, true, R.style.Dialog_animal)
        mBinding.includeEquipmentInfo.tvPlaceOrderEquipmentModel.setOnClickListener { mEquipTypeDialog?.show() }
        
        //初始化时间选择的Dialog
        mTimeBinding = DataBindingUtil.inflate<DialogTimeBinding>(LayoutInflater.from(context), R.layout.dialog_time, null, false)
        mTimeDialog = DialogUtils.getInstance().setBottomDialog(activity, mTimeBinding?.root, true,R.style.Dialog_animal)
        mBinding.includeBasicInfo.etPlaceOrderTime.setOnClickListener { 
            mTimeDialog?.show() }
        //取消时间控件
        mTimeBinding.timeDialogCancel.setOnClickListener { mTimeDialog?.dismiss() }
        //确定日期
        mTimeBinding.timeDialogOk.setOnClickListener { 
            //设置日期显示
            mBinding.includeBasicInfo.etPlaceOrderTime.text = mYear.toString() + "年" + (mMonth + 1) + "月" + mDay + "日"
            mTimeDialog?.dismiss()
        }
        //获取日历对象
        val calendar = Calendar.getInstance()
        mYear = calendar.get(Calendar.YEAR)
        mMonth = calendar.get(Calendar.MONTH)
        mDay = calendar.get(Calendar.DAY_OF_MONTH)
        //设置日期选择的会掉监听
        mTimeBinding.dialogTimeDatapicker.init(mYear, mMonth, mDay, DatePicker.OnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            mYear = year
            mMonth = monthOfYear
            mDay = dayOfMonth
        })
    }

    /**
     * 设置设备的类型
     */
    fun setEquipModel(type: String){
        mBinding.includeEquipmentInfo.tvPlaceOrderEquipmentModel.text = type
        //A:台式机 B：笔记本 C:服务器  D：其它
        when (type) {
            "台式机" -> mEquipType = "A"
            "笔记本" -> mEquipType = "B"
            "服务器" -> mEquipType = "C"
            "其它" -> mEquipType = "D"
        }
        mEquipTypeDialog?.dismiss()
    }

    /**
     * 返回Presenter对象
     */
    override fun getPresenter(): PlaceOrderPresenter? {
        return PlaceOrderPresenter(activity,this,PlaceOrderModel())
    }

    /**
     * 返回当前页面的布局
     */
    override fun getContentLayoutId(): Int {
        return R.layout.frag_place_order
    }

    /**
     * 开启旋转动画
     */
    fun startAnima() {
        if (mAnima != null) {
            mBinding.includeAddrInfo.ivRotate.startAnimation(mAnima)
        }
    }

    /**
     * 停止旋转动画
     */
    fun stopAnima() {
        mBinding.includeAddrInfo.ivRotate.clearAnimation()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            //提交订单
            R.id.bt_place_order ->{
                //拿到订单信息
                val company = mBinding.includeBasicInfo.etPlaceCompanyName.text.toString().trim()
                val name = mBinding.includeBasicInfo.etPlaceOrderName.text.toString().trim()
                val phone = mBinding.includeBasicInfo.etPlaceOrderPhone.text.toString().trim()
                val time = mBinding.includeBasicInfo.etPlaceOrderTime.text.toString().trim()
                //拿到设备信息
                val equipModel = mBinding.includeEquipmentInfo.etPlaceOrderEquipmentModel.text.toString().trim()
                //拿到地址信息
                val addrs = mBinding.includeAddrInfo.etPlaceOrderAddr.text.toString().trim()
                val street = mBinding.includeAddrInfo.etPlaceOrderStreet.text.toString().trim()
                //故障描述
                val describe = mBinding.etPlaceOrderDescribe.text.toString().trim()
                
                //提交订单信息   style A:上门  B：送修  C：其他
                mPresenter?.placeOrder(company,name, phone, time, mServiceType,mEquipType, equipModel, addrs, street, describe)
            }
            R.id.ll_location_again ->{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    checkLocationPermission()
                } else {
                    initLocation()
                }
            }
        }
    }

    /**
     * 对定位权限检测
     */
    private fun checkLocationPermission() {
        RxPermissions(activity).requestEach(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
        ).subscribe{ permission ->
            if (permission.name == android.Manifest.permission.ACCESS_COARSE_LOCATION) {
                isCoarseGranted = permission.granted
            }
            if (permission.name == android.Manifest.permission.ACCESS_FINE_LOCATION) {
                isFineGranted = permission.granted
            }
            if (isCoarseGranted && isFineGranted) {
                //开启定位功能
                initLocation()
            }
        }
    }


    /**
     * 调起定位功能
     */
    private fun initLocation() {
        startAnima()
        mPresenter?.startLocation()
    }
}