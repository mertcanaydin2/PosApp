package com.greedz.posApp.core.utilities.results;

public class DataResult<T> extends Result{

    private T data;

    public DataResult(T data, boolean success) {
        super(success);
        this.data = data;
    }

    public DataResult(T data, boolean success, String message) {
        super(success, message);
        this.data = data; // kullanıcının verdiği data yı döndür demek

    }

    public T getData() {
        return data;
    }

}
