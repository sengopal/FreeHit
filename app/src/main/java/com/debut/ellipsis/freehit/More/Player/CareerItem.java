package com.debut.ellipsis.freehit.More.Player;


import com.google.gson.annotations.SerializedName;

public class CareerItem {
    @SerializedName("img")
    private String img;
    @SerializedName("desc")
    private String desc;
    public String getImg() {
        return img;
    }
    public String getDesc(){return desc;}
   public CareerItem(String img, String desc){
       this.desc=desc;
       this.img=img;
   }
}
