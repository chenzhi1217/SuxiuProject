package com.suxiunet.repair.base

import android.content.pm.ActivityInfo
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.suxiunet.repair.R
import com.suxiunet.repair.databinding.ActBasicBinding
import org.jetbrains.annotations.NotNull

/**
 * author : chenzhi
 * time   : 2017/12/31
 * desc   : 所有Activity的基类
 */
open class BasicActivity : AppCompatActivity() {
    var mBinding: ActBasicBinding? = null
    private var mFragments = HashMap<String ,BasicFragment<out BaseRequest,out IPresenter,Any,out ViewDataBinding>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //禁止横屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        mBinding = DataBindingUtil.setContentView<ActBasicBinding>(this, R.layout.act_basic)
        initToolbar()
    }

    private fun initToolbar() {
        setSupportActionBar(mBinding?.tbBasicAct)
        //隐藏原生的title
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //设置返回键可用
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun setToolBarTitle(title: String) {
        mBinding?.tvBasicActTitle?.text = title
    }

    fun startFragment(@IdRes fragmentViewId: Int, cls: Class<out BasicFragment<out BaseRequest,out IPresenter,Any,out ViewDataBinding>>, tag: String) {
        var fragment = mFragments.get(tag)
        if (fragment == null) {
            try {
                fragment = cls.newInstance()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        startFragment(fragmentViewId, fragment!!, tag)
    }

    fun startFragment(@IdRes fragmentViewId: Int, fragment: BasicFragment<out BaseRequest,out IPresenter,Any,out ViewDataBinding>, @NotNull tag: String) {
        if (fragment != null) {
            mFragments.put(tag, fragment)
            supportFragmentManager.beginTransaction().replace(fragmentViewId,fragment,tag).commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var interceper = false
        var isBack = item?.itemId == android.R.id.home
        if (!interceper && isBack) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}