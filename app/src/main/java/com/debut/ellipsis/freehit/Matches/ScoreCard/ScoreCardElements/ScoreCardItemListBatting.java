package com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardElements;


import java.util.ArrayList;

public class ScoreCardItemListBatting {
    private String Name;
    private ArrayList<ScoreCardItemBatting> Items;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public ArrayList<ScoreCardItemBatting> getItems() {
        return Items;
    }

    public void setItems(ArrayList<ScoreCardItemBatting> Items) {
        this.Items = Items;
    }
}
