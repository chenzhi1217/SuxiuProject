package com.suxiunet.repair.businiss.center.view

import android.widget.ImageView
import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.NomalFragment
import com.suxiunet.repair.businiss.center.contract.ModifySexContract
import com.suxiunet.repair.businiss.center.model.ModifySexModel
import com.suxiunet.repair.businiss.center.presenter.ModifySexPresenter
import com.suxiunet.repair.databinding.FragModifySexBinding

/**
 * author : chenzhi
 * time   : 2018/01/07
 * desc   : 修改性别页面
 */
class ModifySexFragment: NomalFragment<ModifySexPresenter, FragModifySexBinding>(),ModifySexContract.View {
    //记录当前选中的选项
    var checkIndex: Int = 0
    lateinit var images: ArrayList<ImageView>
    
    override fun initView() {
        mBinding.fragment = this
        images = ArrayList()
        images.add(mBinding.ivModifySexMen)
        images.add(mBinding.ivModifySexWomen)
        images.add(mBinding.ivModifySexSecret)
        initCheckState()
    }

    /**
     * 初始化选中状态
     */
    private fun initCheckState() {
        images[0].setImageResource(R.mipmap.icon_choose_nomal)
        images[1].setImageResource(R.mipmap.icon_choose_nomal)
        images[2].setImageResource(R.mipmap.icon_choose_nomal)
        images[checkIndex].setImageResource(R.mipmap.icon_choose_select)
    }

    override fun getPresenter(): ModifySexPresenter? {
        return ModifySexPresenter(activity,this, ModifySexModel())
    }

    override fun getContentLayoutId(): Int {
        return R.layout.frag_modify_sex
    }

    fun switchCheck(index: Int) {
        if (index == checkIndex) {
            return 
        }
        images[checkIndex].setImageResource(R.mipmap.icon_choose_nomal)
        images[index].setImageResource(R.mipmap.icon_choose_select)
        checkIndex = index
    }
}
