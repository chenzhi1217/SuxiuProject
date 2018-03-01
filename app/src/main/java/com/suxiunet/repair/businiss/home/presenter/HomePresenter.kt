package com.suxiunet.repair.businiss.home.presenter

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.telecom.Call
import android.text.TextUtils
import android.view.LayoutInflater
import com.suxiunet.data.util.SpUtil
import com.suxiunet.repair.R
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.base.Constant
import com.suxiunet.repair.businiss.center.view.LoginActivity
import com.suxiunet.repair.businiss.home.contract.HomeContract
import com.suxiunet.repair.businiss.home.view.PlaceOrderActivity
import com.suxiunet.repair.databinding.CallDialogBinding
import com.suxiunet.repair.util.DialogUtils
import com.suxiunet.repair.util.NetWorkUtil
import com.suxiunet.repair.util.ToastUtil
import org.jetbrains.anko.startActivity

/**
 * author : chenzhi
 * time   : 2018/01/04
 * desc   : 首页的Presenter
 */
class HomePresenter : BasicPresenter<HomeContract.View, HomeContract.Model> {
    var mCallDialog: Dialog
    var mCallBinding: CallDialogBinding
    constructor(activity:Activity,view: HomeContract.View,model: HomeContract.Model): super(activity,view,model){
        mCallBinding = DataBindingUtil.inflate<CallDialogBinding>(LayoutInflater.from(mActivity), R.layout.call_dialog, null, false)
        mCallDialog = DialogUtils.getInstance().setCenterDialog(mActivity, mCallBinding.root, true)
        
        mCallBinding.commonDialogCancel.setOnClickListener { mCallDialog?.dismiss() }
        mCallBinding.commonDialogOk.setOnClickListener {
            mCallDialog?.dismiss()
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Constant.companyPhone + ""))
            mActivity.startActivity(intent) }
    }   

    /**
     * 一键下单
     * 
     */
    fun placeOrder() {
        if (!NetWorkUtil.getInstance().isNetWorkConnected(mActivity)) {
            ToastUtil.showToast("请检车您手机的网络~~")
            return
        }
        val token = SpUtil.getString(mActivity, SpUtil.TOKEN_KEY, "")
        if (TextUtils.isEmpty(token)) {
            mActivity.startActivity(Intent(mActivity,LoginActivity::class.java))
            mActivity.startActivity<LoginActivity>()
        } else {
            mActivity.startActivity(Intent(mActivity,PlaceOrderActivity::class.java))
        }
    }

    /**
     * 联系客服
     */
    fun showCallDialog() {
        mCallBinding.commonDialogText2.text = Constant.companyPhone
        mCallDialog.show()
    }
}