package com.debut.ellipsis.freehit.Stats.Team;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;

public class TeamListAdapter extends ArrayAdapter<TeamActivityListItem> {


    public TeamListAdapter(Context context, ArrayList<TeamActivityListItem> items) {

        super(context, 0, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View  listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.country_picker_row, parent, false);
        }

        TeamActivityListItem currentItem = getItem(position);


        TextView statsNameTextView = (TextView) listItemView.findViewById(R.id.row_title);
        statsNameTextView.setText(currentItem.getmStatsName());

        ImageView statsIcon = (ImageView) listItemView.findViewById(R.id.row_icon);
        statsIcon.setImageResource(currentItem.getmStatsIcon());



        return listItemView;
    }

}
