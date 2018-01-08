package com.suxiunet.repair.base

/**
 * author : chenzhi
 * time   : 2018/01/03
 * desc   :
 */
open class BasicRequest {
    private var curPage: Int = 0
    private var pageSize: Int = 10

    /**
     * 下翻页
     */
    fun pgDown() {
        curPage ++
    }

    /**
     * 上翻页
     */
    fun pgUp() {
        curPage --
    }

    fun setPageSize(pageSize: Int) {
        this.pageSize = pageSize
    }
    
    fun getPageSize(): Int{
        return this.pageSize
    }

    /**
     * 获取下一页页码
     */
    fun getNextPage(): Int {
        return curPage + 1
    }

    fun reSetCurPage() {
        curPage = 0
    }
}