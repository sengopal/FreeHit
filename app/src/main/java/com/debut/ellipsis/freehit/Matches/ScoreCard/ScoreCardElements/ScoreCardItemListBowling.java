package com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardElements;


import java.util.ArrayList;

public class ScoreCardItemListBowling {

    private String Name;
    private ArrayList<ScoreCardItemBowling> Items;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public ArrayList<ScoreCardItemBowling> getItems() {
        return Items;
    }

    public void setItems(ArrayList<ScoreCardItemBowling> Items) {
        this.Items = Items;
    }
}
