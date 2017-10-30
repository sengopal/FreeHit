package com.debut.ellipsis.freehit.Social.Polls;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PollCardItem {

    @SerializedName("id")
    public Integer id;

    @SerializedName("ndid")
    public String ndid;

    @SerializedName("question")
    public String question;

    @SerializedName("ctitle")
    public List<String> ctitle = new ArrayList<>();

    @SerializedName("cvotes")
    public List<Integer> cvotes = new ArrayList<>();

    @SerializedName("voteid")
    public String voteid;

    @SerializedName("result")
    public List<PollCardItem> result = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getNdid() {
        return ndid;
    }

    public String getQuestion() {
        return question;
    }

    public String getCtitle(int index) {
        return ctitle.get(index);
    }

    public int searchTitle(String str) {
        return ctitle.indexOf(str) + 1;
    }

    public int getCvotes(int index) {
        return cvotes.get(index);
    }

    public int getTotalVotes() {
        int sum = 0;
        if (cvotes.size() != -1 || cvotes.size()!=0 || !cvotes.isEmpty()) {
            for (int i = 0; i < cvotes.size(); i++) {
                sum += cvotes.get(i);
            }
        }
        return sum;
    }

    public List<PollCardItem> getResults() {
        return result;
    }

    public int getCount() {
        return cvotes.size();
    }

    public String getVoteid() {
        return voteid;
    }
}
