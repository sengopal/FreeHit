package com.debut.ellipsis.freehit.More.Series;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;


public class SeriesListAdapter extends ArrayAdapter<SeriesListItem> {

    public SeriesListAdapter(Context context, ArrayList<SeriesListItem> items) {

        super(context, 0, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.country_picker_row, parent, false);
        }

        SeriesListItem currentItem = getItem(position);


        TextView statsNameTextView = (TextView) listItemView.findViewById(R.id.row_title);
        statsNameTextView.setText(currentItem.getmSeriesName());

        ImageView statsIcon = (ImageView) listItemView.findViewById(R.id.row_icon);
        statsIcon.setImageResource(currentItem.getmSeriesIcon());


        return listItemView;
    }
}
