package com.debut.ellipsis.freehit.More.Player;


import com.google.gson.annotations.SerializedName;

public class CareerItem {
    @SerializedName("desc")
    private String desc;

    public String getDesc() {
        return desc;
    }

    public CareerItem(String desc) {
        this.desc = desc;
    }
}
