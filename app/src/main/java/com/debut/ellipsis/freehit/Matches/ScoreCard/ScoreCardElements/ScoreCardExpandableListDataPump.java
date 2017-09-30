package com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardElements;


import java.util.ArrayList;

public class ScoreCardExpandableListDataPump {

    public static ArrayList<ScoreCardItemListItem> getData() {


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
