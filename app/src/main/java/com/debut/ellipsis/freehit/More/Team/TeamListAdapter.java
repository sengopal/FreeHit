package com.debut.ellipsis.freehit.More.Team;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.debut.ellipsis.freehit.Glide.CustomImageSizeModel;
import com.debut.ellipsis.freehit.Glide.CustomImageSizeModelFutureStudio;
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


        TextView TeamName = (TextView) listItemView.findViewById(R.id.row_title);
        TeamName.setText(currentItem.getmTeamName());

        ImageView TeamIcon = (ImageView) listItemView.findViewById(R.id.row_icon);

        CustomImageSizeModel TeamLogo = new CustomImageSizeModelFutureStudio(currentItem.getmTeamIconURL());

        GlideApp.with(getContext()).load(TeamLogo).apply(new RequestOptions().placeholder(R.drawable.matches)).into(TeamIcon);


        return listItemView;
    }

}