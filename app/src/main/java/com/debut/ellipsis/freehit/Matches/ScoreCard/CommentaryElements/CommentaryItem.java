package com.debut.ellipsis.freehit.Matches.ScoreCard.CommentaryElements;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CommentaryItem {
    @SerializedName("result")
    private List<String> commentary = new ArrayList<>();


    public List<String> getCommentary() {
        return commentary;
    }
}
