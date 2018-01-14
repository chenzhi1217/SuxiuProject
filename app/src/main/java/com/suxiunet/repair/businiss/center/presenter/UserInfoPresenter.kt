package com.suxiunet.repair.businiss.center.presenter

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import com.suxiunet.data.exception.ApiException
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.base.BasicProxy
import com.suxiunet.repair.base.BasicRequest
import com.suxiunet.repair.businiss.center.contract.UserInfoContract
import com.suxiunet.repair.businiss.center.proxy.QuitLoginProxy
import com.suxiunet.repair.businiss.center.view.ModifyNickNameActivity
import com.suxiunet.repair.businiss.center.view.ModifySexActivity
import com.suxiunet.repair.businiss.center.view.UserInfoFragment

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   : 用户信息
 */
class UserInfoPresenter(activity: Activity, view: UserInfoContract.View, model: UserInfoContract.Model) : BasicPresenter<UserInfoContract.View, UserInfoContract.Model>(activity, view, model) {

    var mQuitLoginProxy = QuitLoginProxy(mActivity)
    
    /**
     * 选择头像
     */
    fun checkHeadIcon() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        mActivity.startActivityForResult(intent, UserInfoFragment.REQUEST_IMAGE_CODE)
    }

    /**
     * 更改昵称
     */
    fun modifyNickName() {
        mActivity.startActivityForResult(Intent(mActivity,ModifyNickNameActivity::class.java),UserInfoFragment.REQUEST_MODIFY_NICK_NAME_CODE)
    }

    /**
     * 修改昵称
     */
    fun modifySex() {
        mActivity.startActivityForResult(Intent(mActivity,ModifySexActivity::class.java),UserInfoFragment.REQUEST_MODIFY_SEX_CODE)
    }

    /**
     * 退出登录
     */
    fun quitLogin() {
        mQuitLoginProxy.setCallBack(object : BasicProxy.ProxyCallBack<BasicRequest,Any>{
            override fun onLoadSuccess(req: BasicRequest?, type: BasicProxy.ProxyType, data: Any?) {
                mView.quitLoginSuccess()
            }

            override fun onLoadError(req: BasicRequest?, type: BasicProxy.ProxyType, e: ApiException?) {
                mView.quitLoginError(e)
            }
        })
        
        mQuitLoginProxy.request(BasicRequest(),BasicProxy.ProxyType.REFRESH_DATA)
    }
}