package com.suxiunet.repair.businiss

import android.databinding.ViewDataBinding
import com.suxiunet.repair.R
import com.suxiunet.repair.base.BaseRequest
import com.suxiunet.repair.base.BasicFragment
import com.suxiunet.repair.base.IPresenter
import com.suxiunet.repair.base.RefreshProxy

/**
 * author : chenzhi
 * time   : 2018/01/03
 * desc   :
 */
class TestFragment : BasicFragment<BaseRequest, IPresenter, Any, ViewDataBinding>() {
    override fun needNet(): Boolean {
        return false
    }

    override fun onCreateProxy(): RefreshProxy<BaseRequest, Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRequestData(): BaseRequest {
        return BaseRequest()
    }

    override fun getPresenter(): IPresenter? {
        return object : IPresenter {
            override fun detachView() {
            }
        }
    }

    override fun getContentLayoutId(): Int {
        return R.layout.fragt_test
    }
}