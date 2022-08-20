package com.thief.idea.pojo.vo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ReturnData<T> {
    private boolean isSuccess;
    @NotNull
    private String errorMsg = "未知错误,请联系开发者!";
    @Nullable
    private T data;

    public boolean isSuccess() {
        return this.isSuccess;
    }

    @NotNull
    public String getErrorMsg() {
        return this.errorMsg;
    }

    @Nullable
    public T getData() {
        return this.data;
    }

    @NotNull
    public ReturnData setErrorMsg(@NotNull String errorMsg) {
        this.isSuccess = false;
        this.errorMsg = errorMsg;
        return this;
    }

    @NotNull
    public ReturnData setData(@NotNull T data) {
        this.isSuccess = true;
        this.errorMsg = "";
        this.data = data;
        return this;
    }
}
