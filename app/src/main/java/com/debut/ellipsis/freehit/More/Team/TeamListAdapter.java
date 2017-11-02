package com.debut.ellipsis.freehit.More.Team;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.debut.ellipsis.freehit.Glide.GlideApp;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;

public class TeamListAdapter extends ArrayAdapter<TeamListItem> {


    public TeamListAdapter(Context context, ArrayList<TeamListItem> items) {

        super(context, 0, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.country_picker_row, parent, false);
        }

        TeamListItem currentItem = getItem(position);

        TextView TeamName = listItemView.findViewById(R.id.row_title);
        TeamName.setText(currentItem.getmTeamName());

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            TeamName.setTextColor(Color.WHITE);
        }

        ImageView TeamIcon = listItemView.findViewById(R.id.row_icon);

        RequestBuilder requestBuilder = GlideApp.with(getContext()).load(currentItem.getmTeamIconURL()).placeholder(R.drawable.matches).format(DecodeFormat.PREFER_RGB_565);

        requestBuilder.into(TeamIcon);

        return listItemView;
    }

}