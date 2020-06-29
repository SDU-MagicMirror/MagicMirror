package com.qilu.ec.main.sample;

public class DecorateHistory {
    private String time;
    private String img_base64;

    public DecorateHistory() {
    }

    public DecorateHistory(String time, String img_base64) {
        this.time = time;
        this.img_base64 = img_base64;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg_base64() {
        return img_base64;
    }

    public void setImg_base64(String img_base64) {
        this.img_base64 = img_base64;
    }
}
