package com.suxiunet.repair.businiss.home.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.suxiunet.repair.R
import com.suxiunet.repair.base.baseui.MainActivity
import org.jetbrains.anko.startActivity

/**
 * author : chenzhi
 * time   : 2018/03/24
 * desc   :
 */
class SplashActivity : AppCompatActivity() {
    private var mHandler = object: Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
        }
    }
    private var mSplashImage: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mSplashImage = findViewById<ImageView>(R.id.splash_image)
        Glide.with(this)
                .load(R.drawable.splash_bg)
                .into(mSplashImage)
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT !== 0) {
            finish()
            return
        }
        mHandler.postDelayed(object : Runnable{
            override fun run() {
                startActivity<MainActivity>()
                finish()
            }
        },3000)
    }
}