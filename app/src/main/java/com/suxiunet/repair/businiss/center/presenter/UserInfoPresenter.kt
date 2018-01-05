package com.suxiunet.repair.businiss.center.presenter

import android.app.Activity
import android.content.Intent
import android.graphics.ColorSpace
import android.provider.MediaStore
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.businiss.center.contract.UserInfoContract
import com.suxiunet.repair.businiss.center.view.ModifyNickNameActivity
import com.suxiunet.repair.businiss.center.view.UserInfoFragment

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   : 用户信息
 */
class UserInfoPresenter(activity: Activity, view: UserInfoContract.View, model: UserInfoContract.Model) : BasicPresenter<UserInfoContract.View, UserInfoContract.Model>(activity, view, model) {

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
        mActivity.startActivityForResult(Intent(mActivity,ModifyNickNameActivity::class.java),UserInfoFragment.REQUEST_MODIFY_CODE)
    }
}