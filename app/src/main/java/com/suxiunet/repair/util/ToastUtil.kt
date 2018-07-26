package com.suxiunet.repair.util

import android.widget.Toast
import com.suxiunet.repair.base.baseui.SuXiuApplication


/**
 * Created by 月光和我 on 2017/2/6.
 * 静态吐司工具类
 */
object ToastUtil {

    private var mToast: Toast? = null

    fun showToast(text: String) {
        if (null == mToast) {
            mToast = Toast.makeText(SuXiuApplication.appContext, text, Toast.LENGTH_SHORT)
        }
        mToast!!.setText(text)
        mToast!!.show()
    }


    fun showToast(resId: Int) {
        if (null == mToast) {
            mToast = Toast.makeText(SuXiuApplication.appContext, resId, Toast.LENGTH_SHORT)
        }
        mToast!!.setText(resId)
        mToast!!.show()
    }
}
