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
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515572537680&di=12c10b2ac43a5194107a105ae2235cae&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F01%2F42%2F3c4a16697485ff3717379118de096f7e.jpg")
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517733538696&di=aabff19969d01dc911dbf2fa02786367&imgtype=0&src=http%3A%2F%2Ffile16.zk71.com%2FFile%2FCorpEditInsertImages%2F2017%2F03%2F01%2F0_diannaowei_2033_20170301202127.jpg")
        
        mBinding.vphFragHome.setImageResource(images)
        mBinding.vphFragHome.show()

        mBinding.includeActivity.itemHomePostTv.isMarqueeEnable = true
    }
}