package com.suxiunet.repair.businiss.center.view

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import com.suxiunet.data.entity.user.UserInfoEntity
import com.suxiunet.data.exception.ApiException
import com.suxiunet.data.util.CacheUtil
import com.suxiunet.data.util.SpUtil
import com.suxiunet.data.util.Utils
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
class UserInfoFragment : NomalFragment<UserInfoPresenter, FragUserInfoBinding>(), UserInfoContract.View {
    //退出登录的Dialog
    lateinit var mDialogBinding: DialogQuitLoginBinding
    var mBottomDialog: Dialog? = null

    companion object {
        val PAGE_REQUEST_CODE = 0X0000_0018
        //调用相册的请求码
        val REQUEST_IMAGE_CODE = 0x0000_0010
        //修改昵称的请求码
        var REQUEST_MODIFY_NICK_NAME_CODE = 0X0000_0011
        //修改昵称的请求码
        var REQUEST_MODIFY_SEX_CODE = 0X0000_0012
        //剪切图片的请求码
        val CUT_IMAGE_CODE = 0X0000_0013
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
        mPresenter?.setFragment(this)
    }

    override fun getPresenter(): UserInfoPresenter? {
        return UserInfoPresenter(activity, this, UserInfoModel())
    }

    override fun getContentLayoutId(): Int {
        return R.layout.frag_user_info
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                //请求相册的回调
                REQUEST_IMAGE_CODE -> {
                    val uriPhoto = data?.data
                    cutImage(uriPhoto)
                }
                CUT_IMAGE_CODE -> {
                    val bundle = data?.extras
                    if (null != bundle) {
                        val bitmap = bundle.getParcelable<Bitmap>("data")
                        val file = Utils.bitmap2File(bitmap, Environment.getExternalStorageDirectory(), "head.jpg")
                        //将file文件传到后端
                        mPresenter?.upLoadImage(file)
                    }
                }
                //修改昵称的回调
                REQUEST_MODIFY_NICK_NAME_CODE
                -> {
                    
                }
            }
        }
    }

    /**
     * 对图片进行剪切
     */
    private fun cutImage(uri: Uri?) {
        if (uri == null) {
            return
        }
        //判断是否有sd卡
        if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) {
            ToastUtil.showToast("没有SD卡")
            return
        }
        val intent = Intent("com.android.camera.action.CROP")
        intent.setDataAndType(uri, "image/*")
        //设置裁剪
        intent.putExtra("crop", "true")
        //裁剪的宽高比例
        intent.putExtra("aspectX", 1)
        intent.putExtra("aspectY", 1)
        //裁剪图片的质量
        intent.putExtra("outputX", 256)
        intent.putExtra("outputY", 256)
        //发送数据
        intent.putExtra("return-data", true)
        //跳转
        startActivityForResult(intent, CUT_IMAGE_CODE)
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
        clearUserInfo()
    }

    override fun onResume() {
        super.onResume()
        //初始化用户数据
        try {
            val userInfo = CacheUtil.getInstance().getCacheData(CacheUtil.USER_INFO, UserInfoEntity::class.java)
            mBinding.tvUserNickName.text = userInfo?.loginName
            mBinding.tvUserPhone.text = userInfo?.loginId
            when (userInfo?.gender) {
                "0" -> mBinding.tvUserSex.text = "男"
                "1" -> mBinding.tvUserSex.text = "女"
                else -> mBinding.tvUserSex.text = "保密"
            }
        } catch (e: Exception) {
        }
    }

    /**
     * 清除用户信息
     */
    private fun clearUserInfo() {
        //清除token和用户信息
        try {
            SpUtil.putString(context, SpUtil.TOKEN_KEY, "")
            CacheUtil.getInstance().saveCacheData(null, CacheUtil.USER_INFO)
        } catch (e: Exception) {
        }
        activity.finish()
    }

    /**
     * 退出登录失败
     */
    override fun quitLoginError(e: ApiException?) {
        ToastUtil.showToast(e?.displayMessage ?: "退出失败")
        clearUserInfo()
    }
}
