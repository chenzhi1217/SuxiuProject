package com.suxiunet.repair.businiss.webview

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.LinearLayout
import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.SuXiuApplication
import com.suxiunet.repair.databinding.ActWebViewBinding
import com.suxiunet.repair.view.EsportsWebView

/**
 * author : chenzhi
 * time   : 2018/03/06
 * desc   :
 */
class WebViewActivity : AppCompatActivity(),EsportsWebView.WebViewListener {
    override fun onReceivedTitle(title: String) {
    }

    override fun onReceivedError() {
    }

    override fun onPageFinished(url: String) {
    }

    var mTitle: String = ""
    var mUrl: String = ""
    var mWebView: EsportsWebView? = null
    var mBinding: ActWebViewBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_web_view)
        mBinding = DataBindingUtil.setContentView<ActWebViewBinding>(this, R.layout.act_web_view)
        mTitle = intent.getStringExtra("title")
        mUrl = intent.getStringExtra("url")
        
        mBinding?.ivWebviewDel?.setOnClickListener { 
            finish()
        }
        
        //设置标题
        mBinding?.tvWebViewTitle?.text = mTitle
        
        mWebView = EsportsWebView(SuXiuApplication.appContext, this)
        mWebView?.isScrollContainer = true
        mWebView?.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        mWebView?.isVerticalScrollBarEnabled = false
        
        //添加WebViwe
        mBinding?.flWebView?.addView(mWebView)
        mWebView?.loadUrl(mUrl)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mWebView != null) {
            mWebView?.clearHistory()
            mWebView?.clearCache(true)
            mBinding?.flWebView?.removeAllViews()
            mWebView?.destroy()
        }
    }
}