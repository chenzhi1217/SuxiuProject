package com.suxiunet.repair.businiss.center.contract

import com.suxiunet.data.exception.ApiException
import com.suxiunet.repair.base.IModel
import com.suxiunet.repair.base.IView

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   : 修改昵称
 */
interface ModifyNickNameContract {
    interface View: IView{
        fun modifySuccess(newName: String)
        fun modifyError(e: ApiException?)
    }
    interface Model: IModel
}