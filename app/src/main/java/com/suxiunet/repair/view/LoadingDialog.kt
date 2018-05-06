package com.suxiunet.repair.view

import android.app.AlertDialog
import android.content.Context
import android.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import com.suxiunet.repair.R
import com.victor.loading.rotate.RotateLoading

/**
 * author : chenzhi
 * time   : 2018/04/12
 * desc   :
 */
class LoadingDialog : AlertDialog {
    var loading: RotateLoading? = null

    constructor(context: Context) : super(context, R.style.DialogTheme) {
        this.setCancelable(false)
        this.setCanceledOnTouchOutside(false)
    }

    constructor(context: Context, theme: Int) : super(context, theme) {
        this.setCancelable(false)
        this.setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
        loading = findViewById<RotateLoading>(R.id.loading)
    }

    override fun show() {
        super.show()
        loading?.start()
    }

    override fun dismiss() {
        super.dismiss()
        loading?.stop()
    }
}