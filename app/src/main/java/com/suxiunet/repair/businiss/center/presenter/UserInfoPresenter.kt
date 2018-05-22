package com.suxiunet.repair.businiss.center.presenter

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import android.support.v4.app.Fragment
import com.suxiunet.data.entity.user.UserInfoEntity
import com.suxiunet.data.exception.ApiException
import com.suxiunet.data.util.SpUtil
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.base.BasicProxy
import com.suxiunet.repair.businiss.center.contract.UserInfoContract
import com.suxiunet.repair.businiss.center.proxy.QuitLoginProxy
import com.suxiunet.repair.businiss.center.proxy.UpLoadImageProxy
import com.suxiunet.repair.businiss.center.request.LoginRequest
import com.suxiunet.repair.businiss.center.request.UserInfoRequest
import com.suxiunet.repair.businiss.center.view.ModifyNickNameActivity
import com.suxiunet.repair.businiss.center.view.ModifySexActivity
import com.suxiunet.repair.businiss.center.view.UserInfoFragment
import com.suxiunet.repair.util.ToastUtil
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.HashMap

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   : 用户信息
 */
class UserInfoPresenter : BasicPresenter<UserInfoContract.View, UserInfoContract.Model> {

    var mQuitLoginProxy: QuitLoginProxy
    var mUpLoadProxy: UpLoadImageProxy
    var mRequest = LoginRequest()
    var mUserInfoRequest = UserInfoRequest()
    var mFragment: Fragment? = null

    constructor(activity: Activity, view: UserInfoContract.View, model: UserInfoContract.Model) : super(activity, view, model) {
        mQuitLoginProxy = QuitLoginProxy(mActivity)
        mUpLoadProxy = UpLoadImageProxy(mActivity)
    }

    fun setFragment(fragment: Fragment) {
        this.mFragment = fragment
    }

    /**
     * 选择头像
     */
    fun checkHeadIcon() {
        //请求相册权限
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        mFragment?.startActivityForResult(intent, UserInfoFragment.REQUEST_IMAGE_CODE)
    }
    
    /**
     * 更改昵称
     */
    fun modifyNickName() {
        mActivity.startActivityForResult(Intent(mActivity, ModifyNickNameActivity::class.java), UserInfoFragment.REQUEST_MODIFY_NICK_NAME_CODE)
    }

    /**
     * 修改昵称
     */
    fun modifySex() {
        mActivity.startActivityForResult(Intent(mActivity, ModifySexActivity::class.java), UserInfoFragment.REQUEST_MODIFY_SEX_CODE)
    }

    /**
     * 退出登录
     */
    fun quitLogin() {
        loading?.show()
        mQuitLoginProxy.setCallBack(object : BasicProxy.ProxyCallBack<LoginRequest, Any> {
            override fun onLoadSuccess(req: LoginRequest?, type: BasicProxy.ProxyType, data: Any?) {
                loading?.dismiss()
                mView.quitLoginSuccess()
            }

            override fun onLoadError(req: LoginRequest?, type: BasicProxy.ProxyType, e: ApiException?) {
                loading?.dismiss()
                mView.quitLoginError(e)
            }
        })
        val loginId = SpUtil.getString(mActivity, SpUtil.LOGIN_ID_KEY, "")

        mRequest.setLoginName(loginId)
        mQuitLoginProxy.request(mRequest, BasicProxy.ProxyType.REFRESH_DATA)
    }

    /**
     * 上传头像
     */
    fun upLoadImage(file: File) {
        mUpLoadProxy.setCallBack(object : BasicProxy.ProxyCallBack<UserInfoRequest, UserInfoEntity> {
            override fun onLoadSuccess(req: UserInfoRequest?, type: BasicProxy.ProxyType, data: UserInfoEntity?) {
                ToastUtil.showToast("上传成功")
                mView.imageLoadSuccess(data?.image ?: "")
            }

            override fun onLoadError(req: UserInfoRequest?, type: BasicProxy.ProxyType, e: ApiException?) {
                ToastUtil.showToast("上传失败")
            }

        })
        val loginId = SpUtil.getString(mActivity, SpUtil.LOGIN_ID_KEY, "")

        var requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

        val loginIdDesc = RequestBody.create(MediaType.parse("multipart/form-data"), loginId)
        val loginTypeDesc = RequestBody.create(MediaType.parse("multipart/form-data"), "C")

        val desMap = HashMap<String, String>()
        desMap.put("loginId", loginId)
        desMap.put("loginType", "C")

        mUserInfoRequest.map = desMap
        mUserInfoRequest.file = body

        mUpLoadProxy.request(mUserInfoRequest, BasicProxy.ProxyType.REFRESH_DATA)
    }
}