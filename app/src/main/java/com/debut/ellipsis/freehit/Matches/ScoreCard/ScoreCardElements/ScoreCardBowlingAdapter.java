package com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardElements;


import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardItem;
import com.debut.ellipsis.freehit.R;

import java.util.List;

public class ScoreCardBowlingAdapter extends ArrayAdapter {

    private List<ScoreCardItem.Bowling> BowlingList;


    public ScoreCardBowlingAdapter(Context context, List<ScoreCardItem.Bowling> batting) {
        super(context, 0, batting);
        BowlingList = batting;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_matchscorecard_scorecard_innings_batting_bowling_item_dark, parent, false);

            else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO)
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_matchscorecard_scorecard_innings_batting_bowling_item, parent, false);
        }

        ScoreCardItem.Bowling currentBowler = BowlingList.get(position);
        TextView batsman_value = listItemView.findViewById(R.id.batsman_value);
        TextView runs_value = listItemView.findViewById(R.id.runs_value);
        TextView balls_value = listItemView.findViewById(R.id.balls_value);
        TextView fours_value = listItemView.findViewById(R.id.fours_value);
        TextView sixes_value= listItemView.findViewById(R.id.sixes_value);
        TextView StrikeRate_value = listItemView.findViewById(R.id.StrikeRate_value);

        batsman_value.setText(currentBowler.getName());
        runs_value.setText(currentBowler.getOvers());
        balls_value.setText(currentBowler.getMaidens());
        fours_value.setText(currentBowler.getRuns());
        sixes_value.setText(currentBowler.getWickets());
        StrikeRate_value.setText(currentBowler.getEr());

        return listItemView;

    }
}
