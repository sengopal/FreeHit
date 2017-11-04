package com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardElementsTemp;


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

public class ScoreCardFOWAdapter extends ArrayAdapter {

    private List<ScoreCardItem.FOW> FowList;


    public ScoreCardFOWAdapter(Context context, List<ScoreCardItem.FOW> fow) {
        super(context, 0, fow);
        FowList = fow;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_matchscorecard_scorecard_innings_fow_item_dark, parent, false);

            else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO)
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_matchscorecard_scorecard_innings_fow_item, parent, false);
        }

        ScoreCardItem.FOW currentBatsmanFOW = FowList.get(position);
        TextView FallOfWicket_Value = listItemView.findViewById(R.id.FallOfWicket_Value);
        TextView fow_overs_value = listItemView.findViewById(R.id.fow_overs_value);
        TextView fow_score_value = listItemView.findViewById(R.id.fow_score_value);

        FallOfWicket_Value.setText(currentBatsmanFOW.getName());
        fow_overs_value.setText(currentBatsmanFOW.getOvers());
        fow_score_value.setText(currentBatsmanFOW.getScore());

        return listItemView;

    }
}
