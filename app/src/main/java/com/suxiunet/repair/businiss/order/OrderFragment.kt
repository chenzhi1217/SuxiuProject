package com.suxiunet.repair.businiss.order

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suxiunet.repair.R
import com.suxiunet.repair.databinding.FragOrderBinding

/**
 * author : chenzhi
 * time   : 2017/12/30
 * desc   :
 */
class OrderFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragOrderBinding>(inflater, R.layout.frag_order, container, false)
        return binding.root
    }
}