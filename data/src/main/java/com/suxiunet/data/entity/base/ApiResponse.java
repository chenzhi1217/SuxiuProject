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
    private int code;//比如200代表成功 
    /**
     * 用于存放失败信息
     */
    private String message;
    /**
     * 网络请求返回的数据
     */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
