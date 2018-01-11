package com.suxiunet.repair.businiss.center.presenter

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import com.suxiunet.repair.util.ToastUtil
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.base.Constant
import com.suxiunet.repair.businiss.center.contract.CenterContract
import com.suxiunet.repair.businiss.center.view.AboutActivity
import com.suxiunet.repair.businiss.center.view.FeedBackActivity
import com.suxiunet.repair.businiss.center.view.LoginActivity
import com.suxiunet.repair.businiss.center.view.UserInfoActivity

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   : 个人中心
 */
class CenterPresenter(activity: Activity, view: CenterContract.View, model: CenterContract.Model) : BasicPresenter<CenterContract.View, CenterContract.Model>(activity, view, model) {
    val WEIXIN_CHATTING_MIMETYPE = "vnd.android.cursor.item/vnd.com.tencent.mm.chatting.profile"//微信聊天

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

    /**
     * 关于我们
     */
    fun aboutUs() {
//        mActivity.startActivity(Intent(mActivity,LoginActivity::class.java))
        mActivity.startActivity(Intent(mActivity,AboutActivity::class.java))
    }

    /**
     * 用户反馈
     */
    fun feedBack() {
        mActivity.startActivity(Intent(mActivity,FeedBackActivity::class.java))
    }

    /**
     * 联系客服
     */
    fun contactCustomerService() {
        try {
            val url = "mqqwpa://im/chat?chat_type=wpa&uin="+Constant.companyQQ+""
            mActivity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: ActivityNotFoundException) {
            ToastUtil.showToast("您还没有安装手机QQ")
        }
    }
   
}