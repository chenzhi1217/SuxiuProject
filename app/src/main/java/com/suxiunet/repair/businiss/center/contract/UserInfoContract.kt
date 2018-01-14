package com.suxiunet.repair.businiss.center.contract

import com.suxiunet.data.exception.ApiException
import com.suxiunet.repair.base.IModel
import com.suxiunet.repair.base.IView

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   : 用户信息
 */
interface UserInfoContract {
    interface View: IView{
        fun quitLoginSuccess()
        fun quitLoginError(e: ApiException?)
    }
    
    interface Model:IModel
}