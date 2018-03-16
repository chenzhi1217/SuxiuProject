package com.suxiunet.repair.view

import android.R
import android.content.Context
import android.net.http.SslError
import android.os.Build
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import android.widget.AbsoluteLayout
import android.widget.ProgressBar

/**
 * author : chenzhi
 * time   : 2018/03/06
 * desc   :
 */
class EsportsWebView(context: Context, var mWebViewListener: WebViewListener) : WebView(context) {
    val progressbar: ProgressBar = ProgressBar(getContext(), null, R.attr.progressBarStyleHorizontal)
    init {
        progressbar.layoutParams = AbsoluteLayout.LayoutParams(AbsoluteLayout.LayoutParams.MATCH_PARENT, 10, 0, 0)
//        progressbar.progressDrawable = resources.getDrawable(com.zdan91.bills.R.drawable.webview_pb_style)
        addView(progressbar)

        val settings = this.settings
        settings.javaScriptEnabled = true
        settings.allowFileAccess = true
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.domStorageEnabled = true
        settings.useWideViewPort = true
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        settings.loadWithOverviewMode = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.databaseEnabled = true
        //webview在安卓5.0之前默认允许其加载混合网络协议内容
        // 在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        setWebViewClient(MyWebViewClient())
        setWebChromeClient(MyWebChromeClient())
    }

    inner class MyWebViewClient: WebViewClient(){
        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            //接受所有网站的证书，解决Https拦截问题
            handler?.proceed()
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
        }

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun shouldOverrideKeyEvent(view: WebView?, event: KeyEvent?): Boolean {
            return super.shouldOverrideKeyEvent(view, event)
        }
    }

    inner class MyWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            if (progressbar == null) {
                return
            }
            if (newProgress == 100) {
                progressbar.visibility = View.GONE
            } else {
                if (progressbar.visibility == View.GONE) {
                    progressbar.visibility = View.VISIBLE
                }
                progressbar.progress = newProgress
            }
        }
    }

    interface WebViewListener{
        fun onReceivedTitle(title: String)

        fun onReceivedError()

        fun onPageFinished(url: String)
    }
}