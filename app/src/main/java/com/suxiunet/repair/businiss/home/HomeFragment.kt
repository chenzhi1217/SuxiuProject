package com.suxiunet.repair.businiss.home

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suxiunet.repair.R
import com.suxiunet.repair.businiss.TestActivity
import com.suxiunet.repair.databinding.FragHomeBinding

/**
 * author : chenzhi
 * time   : 2017/12/30
 * desc   :
 */
class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragHomeBinding>(inflater, R.layout.frag_home, container, false)
        binding.root.setOnClickListener { 
            startActivity(Intent(activity,TestActivity::class.java))
        }
        return binding.root
    }
}