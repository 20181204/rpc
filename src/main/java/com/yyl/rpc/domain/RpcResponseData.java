package com.yyl.rpc.domain;

/**
 * ${DESCRIPTION}
 *
 * @author yyl
 * @date 2018年11月29日 13:26
 */
public class RpcResponseData<T> {
    private T result;
    private boolean success;
    private String errorMessage;
    private String cause;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
