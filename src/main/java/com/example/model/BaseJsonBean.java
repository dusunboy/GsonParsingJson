package com.example.model;

import java.util.ArrayList;

/**
 * Created by vincent on 2017-01-17.
 */

public class BaseJsonBean<T> {

    private String status;
    private String message;
    private T data;
    private ArrayList<T> dataList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public ArrayList<T> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<T> dataList) {
        this.dataList = dataList;
    }
}
