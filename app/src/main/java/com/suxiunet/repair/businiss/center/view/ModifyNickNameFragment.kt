package com.suxiunet.repair.businiss.center.view

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.suxiunet.data.entity.user.UserInfoEntity
import com.suxiunet.data.exception.ApiException
import com.suxiunet.data.util.CacheUtil
import com.suxiunet.data.util.SpUtil
import com.suxiunet.data.util.Utils
import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.NomalFragment
import com.suxiunet.repair.businiss.center.contract.ModifyNickNameContract
import com.suxiunet.repair.businiss.center.model.ModifyNickNameModel
import com.suxiunet.repair.businiss.center.presenter.ModifyNickNamePresenter
import com.suxiunet.repair.databinding.FragModifyNickNameBinding
import com.suxiunet.repair.util.ToastUtil
import okhttp3.Cache

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   : 修改昵称
 */
class ModifyNickNameFragment: NomalFragment<ModifyNickNamePresenter, FragModifyNickNameBinding>(),ModifyNickNameContract.View {

    override fun initView() {
        //初始化数据展示
        val userInfo = CacheUtil.getInstance().getCacheData(CacheUtil.USER_INFO, UserInfoEntity::class.java)
        mBinding.etModifyNickName.setText(userInfo.loginName)
        Utils.setCursorLocationEnd(mBinding.etModifyNickName)
    }

    /**
     * 创建ToolBar菜单按钮
     */
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.confirm_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.confirm_modify) {
            //拿到用户昵称
            val newName = mBinding.etModifyNickName.text.toString().trim()
            mPresenter?.modifyNickName(newName)
        }
        return true
    }

    override fun getPresenter(): ModifyNickNamePresenter? {
        return ModifyNickNamePresenter(activity,this,ModifyNickNameModel())
    }

    override fun getContentLayoutId(): Int {
        return R.layout.frag_modify_nick_name
    }

    /**
     * 昵称修改成功
     */
    override fun modifySuccess(newName: String) {
        //跟换本地的昵称
        val userInfo = CacheUtil.getInstance().getCacheData(CacheUtil.USER_INFO, UserInfoEntity::class.java)
        userInfo.loginName = newName
        CacheUtil.getInstance().saveCacheData(userInfo,CacheUtil.USER_INFO)
        activity.finish()
    }

    /**
     * 昵称修改失败
     */
    override fun modifyError(e: ApiException?) {
        ToastUtil.showToast(e?.displayMessage?:"修改失败")
    }
}