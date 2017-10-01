package com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardElements;


import java.util.ArrayList;

public class ScoreCardExpandableListDataPump {

    public static ArrayList<ScoreCardItemListBatting> getDataBatting() {


        String group_names[] = { "WI 1st Innings\n288/6(50.0)\nExtras: 11 (B: 0, LB: 2, NB: 0, WD: 9, P: 0)", "ENG 1st Innings\n294/1(38.0)\nExtras: 6 (B: 0, LB: 0, NB: 0, WD: 6, P: 0)", "WI 2nd Innings\n288/6(50.0)\nExtras: 6 (B: 0, LB: 0, NB: 0, WD: 6, P: 0)", "ENG 2nd Innings\n288/6(50.0)\nExtras: 6 (B: 0, LB: 0, NB: 0, WD: 6, P: 0)" };

        ArrayList<ScoreCardItemListBatting> list = new ArrayList<ScoreCardItemListBatting>();

        ArrayList<ScoreCardItemBatting> ch_list;

        int size = 10;
        int j = 0;

        for (String group_name : group_names) {
            ScoreCardItemListBatting gru = new ScoreCardItemListBatting();
            gru.setName(group_name);

            ch_list = new ArrayList<ScoreCardItemBatting>();
            for (; j < size; j++) {
                ScoreCardItemBatting ch = new ScoreCardItemBatting("christopher henry gayle","c Liam Plunkett b Tom Curran","99","99","999","999","999","999.99");

                ch_list.add(ch);
            }
            gru.setItems(ch_list);
            list.add(gru);

            size = size + 10;
        }

        return list;
    }

    public static ArrayList<ScoreCardItemListBowling> getDataBowling() {


        String group_names[] = { "WI 1st Innings", "ENG 1st Innings"/*, "WI 2nd Innings", "ENG 2nd Innings"*/};

        ArrayList<ScoreCardItemListBowling> list = new ArrayList<ScoreCardItemListBowling>();

        ArrayList<ScoreCardItemBowling> ch_list;

        int size = 10;
        int j = 0;

        for (String group_name : group_names) {
            ScoreCardItemListBowling gru = new ScoreCardItemListBowling();
            gru.setName(group_name);

            ch_list = new ArrayList<ScoreCardItemBowling>();
            for (; j < size; j++) {
                ScoreCardItemBowling ch = new ScoreCardItemBowling("Jake Ball","10","999","99.99","999","99","99","99","999");

                ch_list.add(ch);
            }
            gru.setItems(ch_list);
            list.add(gru);

            size = size + 10;
        }

        return list;
    }
        
    }
