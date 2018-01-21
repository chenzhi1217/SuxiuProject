package com.suxiunet.repair.businiss.center.view

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import com.suxiunet.data.entity.user.UserInfoEntity
import com.suxiunet.data.exception.ApiException
import com.suxiunet.data.util.CacheUtil
import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.NomalFragment
import com.suxiunet.repair.businiss.center.contract.ModifySexContract
import com.suxiunet.repair.businiss.center.model.ModifySexModel
import com.suxiunet.repair.businiss.center.presenter.ModifySexPresenter
import com.suxiunet.repair.databinding.FragModifySexBinding
import com.suxiunet.repair.util.ToastUtil

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
        //初始化性别数据展示
        val userInfo = CacheUtil.getInstance().getCacheData(CacheUtil.USER_INFO, UserInfoEntity::class.java)
        when (userInfo.gender) {
            "0" -> checkIndex = 0
            "1" -> checkIndex = 1
            else -> checkIndex = 2
        }
        initCheckState()
    }

    /**
     * 创建ToolBar菜单按钮
     */
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.confirm_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.confirm_modify) {
            //拿到当前选中的性别
            val gendentState = getGendentState()
            mPresenter?.modifyGendent(gendentState)
        }
        return true
    }

    /**
     * 修改性别成功
     */
    override fun modifyGendentSuccess(gendent: String) {
        //更改本地的用户信息
        val userInfo = CacheUtil.getInstance().getCacheData(CacheUtil.USER_INFO, UserInfoEntity::class.java)
        userInfo.gender = gendent
        CacheUtil.getInstance().saveCacheData(userInfo, CacheUtil.USER_INFO)
        activity.finish()
    }

    /**
     * 修改性别失败
     */
    override fun modifyGendentError(e: ApiException?) {
        ToastUtil.showToast(e?.displayMessage?:"修改失败")
    }

    fun getGendentState(): String {
        when (checkIndex) {
            0 -> return "0"
            1 -> return "1"
            else -> return "2"
        }
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
