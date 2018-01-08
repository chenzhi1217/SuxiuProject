package com.suxiunet.repair.base.adapter


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * author : chenzhi
 * time   : 2018/01/08
 * desc   :
 */
class CommonFragmentPageAdapter(fm:FragmentManager): FragmentStatePagerAdapter(fm) {
    lateinit var mTitles: List<String>
    lateinit var mFragments: List<Fragment>

    constructor(fm: FragmentManager, titles: List<String>, fragments: List<Fragment>) : this(fm){
        this.mTitles = titles
        this.mFragments = fragments
    }
    
    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getCount(): Int {
        return mTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTitles[position]
    }
}