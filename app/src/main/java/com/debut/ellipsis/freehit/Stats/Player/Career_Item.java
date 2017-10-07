package com.debut.ellipsis.freehit.Stats.Player;


import com.google.gson.annotations.SerializedName;

public class Career_Item {
    @SerializedName("img")
    private String img;
    @SerializedName("desc")
    private String desc;
    public String getImg() {
        return img;
    }
    public String getDesc(){return desc;}
   public Career_Item (String img,String desc){
       this.desc=desc;
       this.img=img;
   }
}
