package com.suxiunet.repair.businiss.center.presenter

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.base.Constant
import com.suxiunet.repair.businiss.center.contract.CenterContract
import com.suxiunet.repair.businiss.center.view.UserInfoActivity

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   : 个人中心
 */
class CenterPresenter(activity: Activity, view: CenterContract.View, model: CenterContract.Model) : BasicPresenter<CenterContract.View, CenterContract.Model>(activity, view, model) {

    /**
     * 跳转到个人中心页面
     */
    fun goUserInfo() {
        mActivity.startActivity(Intent(mActivity, UserInfoActivity::class.java))
    }

    /**
     * 联系我们
     */
    fun contactUs() {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+Constant.companyPhone+""))
        mActivity.startActivity(intent)
    }
}