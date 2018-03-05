package com.suxiunet.repair.businiss.center.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.suxiunet.data.entity.user.UserInfoEntity
import com.suxiunet.data.util.CacheUtil
import com.suxiunet.data.util.SpUtil
import com.suxiunet.repair.R
import com.suxiunet.repair.base.Constant
import com.suxiunet.repair.base.baseui.MainActivity
import com.suxiunet.repair.base.baseui.NomalFragment
import com.suxiunet.repair.businiss.center.contract.CenterContract
import com.suxiunet.repair.businiss.center.model.CenterModel
import com.suxiunet.repair.businiss.center.presenter.CenterPresenter
import com.suxiunet.repair.databinding.FragCenterBinding
import com.suxiunet.repair.util.ToastUtil

/**
 * author : chenzhi
 * time   : 2017/12/30
 * desc   : 个人中心页面
 */
class CenterFragment : NomalFragment<CenterPresenter, FragCenterBinding>(), CenterContract.View {
    override fun initView() {
        mBinding.presenter = mPresenter
        mBinding.fragment = this
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as MainActivity).showCenterTitle()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUserInfo()
    }

    override fun onResume() {
        super.onResume()
        setUserInfo()
    }

    override fun getPresenter(): CenterPresenter? {
        return CenterPresenter(activity, this, CenterModel())
    }

    override fun getContentLayoutId(): Int {
        return R.layout.frag_center
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            (activity as MainActivity).showCenterTitle()
            setUserInfo()
        }
    }

    /**
     * 设置用户数据
     */
    fun setUserInfo() {
        val token = SpUtil.getString(context, SpUtil.TOKEN_KEY, "")
        val userInfo = CacheUtil.getInstance().getCacheData(CacheUtil.USER_INFO, UserInfoEntity::class.java)
        mBinding.tvFragCenterNickName.text = if (TextUtils.isEmpty(token)) "立即登录" else userInfo?.loginName?:""

        Glide.with(this)
                .load(Constant.baseImage + userInfo?.url)
                .error(R.mipmap.icon_user_default)
                .placeholder(R.mipmap.icon_user_default)
                .into(mBinding.ivFragCenterUser)
    }
}