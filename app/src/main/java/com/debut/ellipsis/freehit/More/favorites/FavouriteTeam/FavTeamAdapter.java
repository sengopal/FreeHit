package com.debut.ellipsis.freehit.More.favorites.FavouriteTeam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.debut.ellipsis.freehit.More.MoreMain.MoreItem;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;


public class FavTeamAdapter extends ArrayAdapter<MoreItem> {

    public FavTeamAdapter(Context context, ArrayList<MoreItem> items) {

        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.country_picker_row, parent, false);
        }

        MoreItem currentItem = getItem(position);

        TextView statsNameTextView = (TextView) listItemView.findViewById(R.id.row_title);
        statsNameTextView.setText(currentItem.getmMoreName());

        ImageView statsIcon = (ImageView) listItemView.findViewById(R.id.row_icon);
        statsIcon.setImageResource(currentItem.getmMoreIcon());


        return listItemView;
    }
}

