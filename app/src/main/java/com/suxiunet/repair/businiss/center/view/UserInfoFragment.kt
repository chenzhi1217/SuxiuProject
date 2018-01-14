package com.suxiunet.repair.businiss.center.view

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import com.suxiunet.data.entity.user.UserInfoEntity
import com.suxiunet.data.exception.ApiException
import com.suxiunet.data.util.CacheUtil
import com.suxiunet.data.util.SpUtil
import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.NomalFragment
import com.suxiunet.repair.businiss.center.contract.UserInfoContract
import com.suxiunet.repair.businiss.center.model.UserInfoModel
import com.suxiunet.repair.businiss.center.presenter.UserInfoPresenter
import com.suxiunet.repair.databinding.DialogQuitLoginBinding
import com.suxiunet.repair.databinding.FragUserInfoBinding
import com.suxiunet.repair.util.DialogUtils
import com.suxiunet.repair.util.ToastUtil

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   : 用户信息页面
 */
class UserInfoFragment: NomalFragment<UserInfoPresenter, FragUserInfoBinding>(),UserInfoContract.View {
    //退出登录的Dialog
    lateinit var mDialogBinding: DialogQuitLoginBinding
    var mBottomDialog: Dialog? = null
    
    companion object {
        //调用相册的请求码
        val REQUEST_IMAGE_CODE = 0x0000_0010
        //修改昵称的请求码
        var REQUEST_MODIFY_NICK_NAME_CODE = 0X0000_0011
        //修改昵称的请求码
        var REQUEST_MODIFY_SEX_CODE = 0X0000_0012
    }
    override fun initView() {
        mBinding.presenter = mPresenter
        mBinding.fragment = this
        
        //初始化Dialog
        mDialogBinding = DataBindingUtil.inflate<DialogQuitLoginBinding>(LayoutInflater.from(activity), R.layout.dialog_quit_login, null, false)
        mBottomDialog = DialogUtils.getInstance().setBottomDialog(activity, mDialogBinding.root, true, R.style.Dialog_animal)
        mDialogBinding.tvCancel.setOnClickListener { mBottomDialog?.dismiss() }
        mDialogBinding.tvQuitLogin.setOnClickListener { 
            //退出登录
            mPresenter?.quitLogin()
        }
    }

    override fun getPresenter(): UserInfoPresenter? {
        return UserInfoPresenter(activity,this,UserInfoModel())
     }

    override fun getContentLayoutId(): Int {
        return R.layout.frag_user_info
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                //请求相册的回调
                REQUEST_IMAGE_CODE ->{
                    
                }
                //修改昵称的回调
                REQUEST_MODIFY_NICK_NAME_CODE ->{
                    
                }
            }
        }
    }

    /**
     * 点击退出登录
     */
    fun quitLogin() {
        mBottomDialog?.show()
    }

    /**
     * 退出登录成功
     */
    override fun quitLoginSuccess() {
        ToastUtil.showToast("退出成功")
        //清楚token和用户信息
        SpUtil.putString(context,SpUtil.TOKEN_KEY,"")
        CacheUtil.getInstance().saveCacheData(null,CacheUtil.USER_INFO)
        activity.finish()
    }

    /**
     * 退出登录失败
     */
    override fun quitLoginError(e: ApiException?) {
    }
}
