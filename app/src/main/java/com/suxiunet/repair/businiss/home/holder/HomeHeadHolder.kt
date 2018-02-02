package com.suxiunet.repair.businiss.home.holder

import android.view.ViewGroup
import com.suxiunet.repair.base.BasicHolder
import com.suxiunet.repair.businiss.home.presenter.HomePresenter
import com.suxiunet.repair.databinding.FragHomeBinding

/**
 * author : chenzhi
 * time   : 2018/02/01
 * desc   :
 */
class HomeHeadHolder : BasicHolder<Any, FragHomeBinding> {
    private var mPresenter: HomePresenter?

    constructor(parent: ViewGroup?, resId: Int, presenter: HomePresenter?) : super(parent, resId) {
        this.mPresenter = presenter
    }

    override fun bindData(data: Any?) {
        mBinding.presenter = mPresenter

        var images: ArrayList<String> = ArrayList()
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515572537680&di=827c5986c6ecc778ed7ac8a65f0e0139&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2F00%2F00%2F69%2F40%2F9ecb5c0b6dd4471000559917b2c56d58.jpg")
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515562245761&di=f0528f7502f46366496a4168fc1d5cbe&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F03%2F48%2F3847e4a7a58e5644768bdc34a0f09148.jpg")
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515572537680&di=12c10b2ac43a5194107a105ae2235cae&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F01%2F42%2F3c4a16697485ff3717379118de096f7e.jpg")
        mBinding.vphFragHome.setImageResource(images)
        mBinding.vphFragHome.show()
    }
}