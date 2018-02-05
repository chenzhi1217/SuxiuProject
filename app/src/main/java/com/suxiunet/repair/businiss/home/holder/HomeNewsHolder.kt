package com.suxiunet.repair.businiss.home.holder

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.suxiunet.repair.R
import com.suxiunet.repair.base.BasicHolder
import com.suxiunet.repair.businiss.home.presenter.HomePresenter
import com.suxiunet.repair.databinding.ItemHomeNewsBinding

/**
 * author : chenzhi
 * time   : 2018/02/01
 * desc   :
 */
class HomeNewsHolder(var parent: ViewGroup?, resId: Int, var presenter: HomePresenter?): BasicHolder<Any,ItemHomeNewsBinding>(parent,resId) {
    override fun bindData(data: Any?) {
        Glide.with(parent?.context)
                .load(R.mipmap.icon_test)
                .into(mBinding.ivNews)
    }
}