package com.suxiunet.repair.base

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.RadioGroup
import com.suxiunet.data.util.Utils
import com.suxiunet.repair.R
import com.suxiunet.repair.businiss.center.CenterFragment
import com.suxiunet.repair.businiss.home.HomeFragment
import com.suxiunet.repair.businiss.order.OrderFragment
import com.suxiunet.repair.databinding.ActMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    
    var mFragments: ArrayList<Fragment> = ArrayList()
    var mBinding: ActMainBinding? = null
    var mIndex: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView<ActMainBinding>(this, R.layout.act_main)
        initView()
        //初始化ToolBar
        initToolBar()
        initFragment()
    }

    private fun initFragment() {
        val homeFragment = HomeFragment()
        val orderFragment = OrderFragment()
        val centerFragment = CenterFragment()
        mFragments.add(homeFragment)
        mFragments.add(orderFragment)
        mFragments.add(centerFragment)
        
        supportFragmentManager.beginTransaction().add(R.id.main_framlayout,homeFragment).show(homeFragment).commit()
        changeTitle("首页")
    }

    private fun initView() {
        //初始化RadioGroup的选中监听事件
        mBinding?.mainRg?.setOnCheckedChangeListener(onCheckedChangeListener)

        //设置RadioButton图片的大小
        Utils.setRadioButtonSize(this, R.drawable.selector_lable_home, mBinding?.rbMainHome, 22)
        Utils.setRadioButtonSize(this, R.drawable.selector_lable_order, mBinding?.rbMainOrder, 22)
        Utils.setRadioButtonSize(this, R.drawable.selector_lable_center, mBinding?.rbMainCenter, 22)
    }

    /**
     * 初始化ToolBar
     */
    private fun initToolBar() {
        setSupportActionBar(mBinding?.tbMain)
        //取消返回键
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        //隐藏原生标题
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
    /**
     * RadigButton的选中监听
     */
    private val onCheckedChangeListener = object : RadioGroup.OnCheckedChangeListener {
        override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
            when (checkedId) {
                R.id.rb_main_home -> {
                    setCheckedFragment(0)
                    changeTitle("首页")
                }
                R.id.rb_main_order -> {
                    setCheckedFragment(1)
                    changeTitle("订单")
                }
                R.id.rb_main_center ->{
                    setCheckedFragment(2)
                    changeTitle("个人中心")
                }
            }
        }
    }

    private fun changeTitle(title: String) {
        mBinding?.tvMainTitle?.text = title
    }

    private fun setCheckedFragment(index: Int) {
        if (index == mIndex) {
            return 
        }
        val curFragment = mFragments[index]
        //开启一个事物
        val transaction = supportFragmentManager.beginTransaction()
        //隐藏上一个Fragment
        transaction.hide(mFragments[mIndex])
        //显示当前Fragment
        if (!curFragment.isAdded) {
            transaction.add(R.id.main_framlayout, curFragment)
        } else {
            transaction.show(curFragment)
        }
        //提交事务
        transaction.commit()
        mIndex = index
    }
}
