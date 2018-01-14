package com.suxiunet.repair.businiss.center.contract

import com.suxiunet.data.entity.user.UserInfoEntity
import com.suxiunet.data.exception.ApiException
import com.suxiunet.repair.base.IModel
import com.suxiunet.repair.base.IView

/**
 * author : chenzhi
 * time   : 2018/01/05
 * desc   : 登录
 */
interface LoginContract {
    interface View: IView{
        fun loginSuccess(data: UserInfoEntity?)
        fun loginError(e: ApiException?)
    }
    interface Model: IModel
}