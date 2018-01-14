package com.suxiunet.data.entity.base;

/**
 * author : chenzhi
 * time   : 2018/01/04
 * desc   :
 */
public class ApiResponse<T> {
    /**
     * 根据code判断请求的成功与失败
     */
    private int retCode;//比如200代表成功 
    /**
     * 用于存放失败信息
     */
    private String retMsg;
    /**
     * 网络请求返回的数据
     */
    private T retData;

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public T getRetData() {
        return retData;
    }

    public void setRetData(T retData) {
        this.retData = retData;
    }
}
