package com.suxiunet.repair.businiss.center.request

import com.suxiunet.repair.base.BasicRequest

/**
 * author : chenzhi
 * time   : 2018/01/16
 * desc   : 意见反馈的请求参数
 */
class SuggestionsRequest : BasicRequest() {
    var suggestions = ""
    var loginId: String = ""
}