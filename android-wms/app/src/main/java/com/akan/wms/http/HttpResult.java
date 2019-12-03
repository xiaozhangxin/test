package com.akan.wms.http;

/**
 * Created by sh-lx on 2017/5/31.
 */

public class HttpResult<T> {

    //  判断标示
    private String status;

    private String error;

    private T data;




    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
