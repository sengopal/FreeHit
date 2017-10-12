package com.debut.ellipsis.freehit.More.Player;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class player_parsing {
    @SerializedName("name")
    private String name;
    @SerializedName("link")
    private String link;
    @SerializedName("result")
    private List<player_parsing> result = new ArrayList<>();


    public player_parsing(String name,String link){
        this.link=link;
        this.name=name;
    }

    public String getName(){return name;}
    public String getLink(){return link;}

    public List<player_parsing> getResult() {
        return result;
    }
}
