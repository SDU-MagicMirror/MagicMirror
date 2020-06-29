package com.qilu.ec.main.sample.example_create;

public class ExampleCreate {
    private int code;
    private ExampleCreate_Data data;
    private String msg;

    public ExampleCreate(int code, ExampleCreate_Data data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ExampleCreate_Data getData() {
        return data;
    }

    public void setData(ExampleCreate_Data data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
