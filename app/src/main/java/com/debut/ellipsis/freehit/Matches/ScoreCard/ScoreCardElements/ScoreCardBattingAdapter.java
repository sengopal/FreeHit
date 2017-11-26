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

public class ScoreCardBattingAdapter extends ArrayAdapter {

    private List<ScoreCardItem.Batting> BattingList;


    public ScoreCardBattingAdapter(Context context, List<ScoreCardItem.Batting> batting) {
        super(context, 0, batting);
        BattingList = batting;
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

        ScoreCardItem.Batting currentBatsman = BattingList.get(position);
        TextView batsman_value = listItemView.findViewById(R.id.batsman_value);
        TextView player_out_type = listItemView.findViewById(R.id.player_out_type);
        TextView runs_value = listItemView.findViewById(R.id.runs_value);
        TextView balls_value = listItemView.findViewById(R.id.balls_value);
        TextView fours_value = listItemView.findViewById(R.id.fours_value);
        TextView sixes_value = listItemView.findViewById(R.id.sixes_value);
        TextView StrikeRate_value = listItemView.findViewById(R.id.StrikeRate_value);
        TextView player_yet_average_label = listItemView.findViewById(R.id.player_yet_average_label);
        TextView player_yet_average_value = listItemView.findViewById(R.id.player_yet_average_value);
        TextView player_yet_sr_label = listItemView.findViewById(R.id.player_yet_sr_label);
        TextView player_yet_sr_value = listItemView.findViewById(R.id.player_yet_sr_value);
        View divider = listItemView.findViewById(R.id.sr_average_view);

        batsman_value.setText(currentBatsman.getName());
        if (currentBatsman.getStatus().equals("-")) {
            player_out_type.setVisibility(View.INVISIBLE);
            player_yet_average_label.setVisibility(View.VISIBLE);
            player_yet_average_value.setVisibility(View.VISIBLE);
            player_yet_sr_label.setVisibility(View.VISIBLE);
            player_yet_sr_value.setVisibility(View.VISIBLE);
            divider.setVisibility(View.VISIBLE);
            player_yet_average_value.setText(currentBatsman.getAvg());
            player_yet_sr_value.setText(currentBatsman.getSr());
            StrikeRate_value.setText("");
        } else {
            player_out_type.setText(currentBatsman.getStatus());
            runs_value.setText(currentBatsman.getRuns());
            balls_value.setText(currentBatsman.getBalls());
            fours_value.setText(currentBatsman.getFours());
            sixes_value.setText(currentBatsman.getSixes());
            StrikeRate_value.setText(currentBatsman.getSr());
        }
        return listItemView;

    }
}
