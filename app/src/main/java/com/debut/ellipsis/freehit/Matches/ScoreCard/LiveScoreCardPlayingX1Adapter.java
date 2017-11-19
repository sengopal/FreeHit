package com.debut.ellipsis.freehit.Matches.ScoreCard;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;

import java.util.List;

public class LiveScoreCardPlayingX1Adapter extends ArrayAdapter {

    private List<ScoreCardItem.LineUp> PlayerList;


    public LiveScoreCardPlayingX1Adapter(Context context, List<ScoreCardItem.LineUp> player) {
        super(context, 0, player);
        PlayerList = player;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.country_picker_row, parent, false);

        }

        ScoreCardItem.LineUp currentBatsman = PlayerList.get(position);
        TextView batsman_value = listItemView.findViewById(R.id.row_title);

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
        {
            batsman_value.setTextColor(Color.WHITE);
        }

        batsman_value.setText(currentBatsman.getName());

        return listItemView;

    }
}
