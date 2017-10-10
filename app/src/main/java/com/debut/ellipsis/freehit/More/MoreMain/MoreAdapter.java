package com.debut.ellipsis.freehit.More.MoreMain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;


public class MoreAdapter extends ArrayAdapter<MoreItem> {

    public MoreAdapter(Context context, ArrayList<MoreItem> items) {

        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_more, parent, false);
        }

        MoreItem currentItem = getItem(position);


        TextView statsNameTextView = (TextView) listItemView.findViewById(R.id.stats_name);
        statsNameTextView.setText(currentItem.getmStatsName());

        ImageView statsIcon = (ImageView) listItemView.findViewById(R.id.stats_icon);
        statsIcon.setImageResource(currentItem.getmStatsIcon());


        return listItemView;
    }
}
