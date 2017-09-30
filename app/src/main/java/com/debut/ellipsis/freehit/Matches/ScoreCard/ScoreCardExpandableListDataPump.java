package com.debut.ellipsis.freehit.Matches.ScoreCard;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScoreCardExpandableListDataPump {

    public static ArrayList<ScoreCardItemListItem> getData() {
        /*HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();*/

        /*List<String> team1 = new ArrayList<String>();
        team1.add("India");
        team1.add("RCB");
        team1.add("New Zealand");
        team1.add("England");
        team1.add("South Africa");

        List<String> team2 = new ArrayList<String>();
        team2.add("Manchester United");
        team2.add("Real Madrid C.F.");
        team2.add("Leicester City F.C.");
        team2.add("Paris Saint-Germain F.C.");
        team2.add("Manchester United F.C.");


        expandableListDetail.put("Team 1", team1);
        expandableListDetail.put("Team 2", team2);

        return expandableListDetail;*/

        String group_names[] = { "TEAM 1 1st Innings", "TEAM 2 1st Innings", "TEAM 1 2nd Innings", "TEAM 2 2nd Innings" };

        ArrayList<ScoreCardItemListItem> list = new ArrayList<ScoreCardItemListItem>();

        ArrayList<ScoreCardItem> ch_list;

        int size = 10;
        int j = 0;

        for (String group_name : group_names) {
            ScoreCardItemListItem gru = new ScoreCardItemListItem();
            gru.setName(group_name);

            ch_list = new ArrayList<ScoreCardItem>();
            for (; j < size; j++) {
                ScoreCardItem ch = new ScoreCardItem("christopher henry gayle","c Liam Plunkett b Tom Curran","99","99","999","999","999","999.99");

                ch_list.add(ch);
            }
            gru.setItems(ch_list);
            list.add(gru);

            size = size + 10;
        }

        return list;
    }
        
        
        
    }
