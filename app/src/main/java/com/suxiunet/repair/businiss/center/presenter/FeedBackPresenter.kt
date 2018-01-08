package com.suxiunet.repair.businiss.center.presenter

import android.app.Activity
import com.suxiunet.repair.base.BasicPresenter
import com.suxiunet.repair.businiss.center.contract.FeedBackContract

/**
 * author : chenzhi
 * time   : 2018/01/08
 * desc   : 用户反馈
 */
class FeedBackPresenter(activity:Activity,view: FeedBackContract.View,model: FeedBackContract.Model): BasicPresenter<FeedBackContract.View, FeedBackContract.Model>(activity,view,model) {
}