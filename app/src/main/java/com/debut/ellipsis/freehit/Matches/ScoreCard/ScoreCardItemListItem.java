package com.debut.ellipsis.freehit.Matches.ScoreCard;


import java.util.ArrayList;

public class ScoreCardItemListItem {

    private String Name;
    private ArrayList<ScoreCardItem> Items;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public ArrayList<ScoreCardItem> getItems() {
        return Items;
    }

    public void setItems(ArrayList<ScoreCardItem> Items) {
        this.Items = Items;
    }
}
