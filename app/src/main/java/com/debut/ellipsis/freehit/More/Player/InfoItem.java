package com.debut.ellipsis.freehit.More.Player;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class InfoItem {
    @SerializedName("name")
    private String name;

    @SerializedName("nationality")
    private String nationality;

    @SerializedName("dob")
    private String dob;

    @SerializedName("age")
    private String age;

    @SerializedName("batstyle")
    private String batstyle;

    @SerializedName("bowlstyle")
    private String bowlstyle;

    @SerializedName("teamsplayed")
    private String teamsplayed;

    @SerializedName("img")
    private String img;

    @SerializedName("manofthematch")
    private List<String> manofthematch;

    @SerializedName("batrank")
    private List<String> batrank;

    @SerializedName("bowlrank")
    private List<String> bowlrank;

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getDob() {
        return dob;
    }

    public String getAge() {
        return age;
    }

    public String getBatstyle() {
        return batstyle;
    }

    public String getBowlstyle() {
        return bowlstyle;
    }

    public String getTeamsplayed() {
        return teamsplayed;
    }

    public String getImg() {
        return img;
    }

    public List<String> getManofthematch() {
        return manofthematch;
    }

    public List<String> getBatrank() {
        return batrank;
    }

    public List<String> getBowlrank() {
        return bowlrank;
    }
}
