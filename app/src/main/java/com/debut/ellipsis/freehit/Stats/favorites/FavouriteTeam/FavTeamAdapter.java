package com.debut.ellipsis.freehit.Stats.favorites.FavouriteTeam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;
import com.debut.ellipsis.freehit.Stats.MoreMain.MoreItem;

import java.util.ArrayList;


public class FavTeamAdapter extends ArrayAdapter<MoreItem> {

    public FavTeamAdapter(Context context, ArrayList<MoreItem> items) {

        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_more_fav_team_team_item, parent, false);
        }

        MoreItem currentItem = getItem(position);

        TextView statsNameTextView = (TextView) listItemView.findViewById(R.id.FavTeamCategoryName);
        statsNameTextView.setText(currentItem.getmStatsName());

        ImageView statsIcon = (ImageView) listItemView.findViewById(R.id.arrow);
        statsIcon.setImageResource(currentItem.getmStatsIcon());


        return listItemView;
    }
}

