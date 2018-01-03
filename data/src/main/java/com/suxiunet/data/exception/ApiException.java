package com.suxiunet.data.exception;

/**
 * 描述 所有网络异常的基类。
 * 创建人 kelin
 * 创建时间 2017/5/30  下午4:40
 * 版本 v 1.0.0
 */

public class ApiException extends RuntimeException {
    private String displayMessage;
    private int displayCode;

    /**
     * Constructs a new {@code RuntimeException} that includes the current stack
     * trace.
     */
    public ApiException() {
        this("");
    }

    /**
     * Constructs a new {@code RuntimeException} with the current stack trace
     * and the specified detail message.
     *
     * @param detailMessage the detail message for this exception.
     */
    public ApiException(String detailMessage) {
        this(detailMessage, 0);
    }

    /**
     * Constructs a new {@code RuntimeException} with the current stack trace,
     * the specified detail message and the specified cause.
     *
     * @param detailMessage the detail message for this exception.
     * @param code
     */
    public ApiException(String detailMessage, int code) {
        super(detailMessage);
        setDisplayMessage(detailMessage);
        setDisplayCode(code);
    }
    
    

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public void setDisplayCode(int code) {
        this.displayCode = code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }
}
