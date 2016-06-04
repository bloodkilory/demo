package com.example.exception;

/**
 * @author yangkun
 *         generate on 16/6/4
 */
public class FileHandleException extends RuntimeException {

    private String msg;
    private Integer code;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public FileHandleException() {
        super();
    }

    public FileHandleException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public FileHandleException(int code, String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

}
