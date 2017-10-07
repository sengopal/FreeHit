package com.debut.ellipsis.freehit.Stats.Player;

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


    public InfoItem(String name, String nationality, String dob, String age, String batstyle, String bowlstyle, String teamsplayed, String img, List<String> batrank, List<String> bowlrank, List<String> manofthematch) {

        this.name = name;
        this.nationality = nationality;
        this.dob = dob;
        this.age = age;
        this.batstyle = batstyle;
        this.bowlstyle = bowlstyle;
        this.teamsplayed = teamsplayed;
        this.img = img;
        this.bowlstyle = bowlstyle;
        this.batrank = batrank;
        this.bowlrank = bowlrank;
        this.manofthematch = manofthematch;
    }

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
