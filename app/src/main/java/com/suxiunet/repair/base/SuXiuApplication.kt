package com.suxiunet.repair.base

import android.app.Application

/**
 * author : chenzhi
 * time   : 2018/01/03
 * desc   : 
 */
class SuXiuApplication : Application() {
    companion object {
        lateinit var appContext: SuXiuApplication
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}