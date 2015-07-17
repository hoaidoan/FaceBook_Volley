package com.example.facebook_volley.data;

import java.io.Serializable;

/**
 * Created by Doan Vo Viet Hoai on 6/22/2015.
 */
public class MessengerItem implements Serializable {
    int img;
    String name, message, time;

    public MessengerItem() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
