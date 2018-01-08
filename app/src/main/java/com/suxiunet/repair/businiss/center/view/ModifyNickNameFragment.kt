package com.suxiunet.repair.businiss.center.view

import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.NomalFragment
import com.suxiunet.repair.businiss.center.contract.ModifyNickNameContract
import com.suxiunet.repair.businiss.center.model.ModifyNickNameModel
import com.suxiunet.repair.businiss.center.presenter.ModifyNickNamePresenter
import com.suxiunet.repair.databinding.FragModifyNickNameBinding

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   : 修改昵称
 */
class ModifyNickNameFragment: NomalFragment<ModifyNickNamePresenter, FragModifyNickNameBinding>(),ModifyNickNameContract.View {
    override fun initView() {
        
    }

    override fun getPresenter(): ModifyNickNamePresenter? {
        return ModifyNickNamePresenter(activity,this,ModifyNickNameModel())
    }

    override fun getContentLayoutId(): Int {
        return R.layout.frag_modify_nick_name
    }
}