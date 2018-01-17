package com.suxiunet.repair.businiss.center.contract

import com.suxiunet.data.exception.ApiException
import com.suxiunet.repair.base.IModel
import com.suxiunet.repair.base.IView

/**
 * author : chenzhi
 * time   : 2018/01/07
 * desc   : 修改性别
 */
interface ModifySexContract {
    interface View: IView{
        fun modifyGendentSuccess(gendent: String)
        fun modifyGendentError(e: ApiException?)
    }
    interface Model: IModel
}