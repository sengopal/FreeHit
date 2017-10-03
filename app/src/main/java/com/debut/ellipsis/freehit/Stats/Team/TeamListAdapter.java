package com.debut.ellipsis.freehit.Stats.Team;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;
import com.debut.ellipsis.freehit.Stats.StatsMain.StatsItem;

import java.util.ArrayList;

public class TeamListAdapter extends ArrayAdapter<StatsItem> {
    public TeamListAdapter(Context context, ArrayList<StatsItem> items) {

        super(context, 0, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.stats_team_list_item, parent, false);
        }

        final StatsItem currentItem = getItem(position);


        TextView statsNameTextView = (TextView) listItemView.findViewById(R.id.stats_name);
        statsNameTextView.setText(currentItem.getmStatsName());

        ImageView statsIcon = (ImageView) listItemView.findViewById(R.id.stats_icon);
        statsIcon.setImageResource(currentItem.getmStatsIcon());


        return listItemView;
    }

}
