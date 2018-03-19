package com.suxiunet.repair.businiss.center.presenter

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import com.suxiunet.data.util.SpUtil
import com.suxiunet.repair.util.ToastUtil
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.base.Constant
import com.suxiunet.repair.businiss.center.contract.CenterContract
import com.suxiunet.repair.businiss.center.view.*
import com.suxiunet.repair.businiss.order.orderlist.view.OrderActivity
import com.suxiunet.repair.businiss.webview.WebViewActivity

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   : 个人中心
 */
class CenterPresenter(activity: Activity, view: CenterContract.View, model: CenterContract.Model) : BasicPresenter<CenterContract.View, CenterContract.Model>(activity, view, model) {
    val WEIXIN_CHATTING_MIMETYPE = "vnd.android.cursor.item/vnd.com.tencent.mm.chatting.profile"//微信聊天

    /**
     * 点击用户头像
     */
    fun goUserInfo(fragment: CenterFragment) {
        val token = SpUtil.getString(mActivity, SpUtil.TOKEN_KEY, "")
        if (TextUtils.isEmpty(token)) {
            //未登录跳转到登录页面
            mActivity.startActivity(Intent(mActivity, LoginActivity::class.java))
        } else {
            //已经登录跳转到个人中心页面
            fragment.startActivityForResult(Intent(mActivity, UserInfoActivity::class.java),UserInfoFragment.PAGE_REQUEST_CODE)
        }
    }

    /**
     * 联系我们
     */
    fun contactUs() {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Constant.companyPhone + ""))
        mActivity.startActivity(intent)
    }

    /**
     * 用户须知
     */
    fun aboutUs() {
//        mActivity.startActivity(Intent(mActivity, AboutActivity::class.java))
        val intent = Intent(mActivity, WebViewActivity::class.java)
        intent.putExtra("title","用户须知")
        intent.putExtra("url",Constant.USER_URL)
        mActivity.startActivity(intent)
    }

    /**
     * 服务流程
     */
    fun process() {
        val intent = Intent(mActivity, WebViewActivity::class.java)
        intent.putExtra("title","服务流程")
        intent.putExtra("url",Constant.SERVICE_URL)
        mActivity.startActivity(intent)
    }

    /**
     * 用户反馈
     */
    fun feedBack() {
        mActivity.startActivity(Intent(mActivity, FeedBackActivity::class.java))
    }

    /**
     * 联系客服
     */
    fun contactCustomerService() {
        try {
            val url = "mqqwpa://im/chat?chat_type=wpa&uin=" + Constant.companyQQ + ""
            mActivity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: ActivityNotFoundException) {
            ToastUtil.showToast("您还没有安装手机QQ")
        }
    }

    /**
     * 跳转到待付款订单页面
     */
    fun goWaitPayOrder() {
        var intent = Intent(mActivity, OrderActivity::class.java)
        intent.putExtra("title","待付款订单")
        intent.putExtra("type","C")
        mActivity.startActivity(intent)
    }

    /**
     * 跳转到已完成订单页面
     */
    fun goCompanyOrder() {
        var intent = Intent(mActivity, OrderActivity::class.java)
        intent.putExtra("title","已完成订单")
        intent.putExtra("type","D")
        mActivity.startActivity(intent)
    }

}