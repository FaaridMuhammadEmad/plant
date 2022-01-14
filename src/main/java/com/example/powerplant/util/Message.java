package com.example.powerplant.util;

public class Message<T> {
    private String status;
    private String message;
    private int code;
    private T data;

    public Message<T> setStatus(String status) {
        this.status = status;
        return this;
    }

    public Message<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Message<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Message<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

}
