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
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_more_main_item, parent, false);
        }

        MoreItem currentItem = getItem(position);

        ImageView moreArrowIcon = (ImageView) listItemView.findViewById(R.id.arrow_icon);
        moreArrowIcon.setImageResource(R.drawable.arrow);

        TextView moreNameTextView = (TextView) listItemView.findViewById(R.id.row_title);
        moreNameTextView.setText(currentItem.getmMoreName());

        ImageView moreIcon = (ImageView) listItemView.findViewById(R.id.row_icon);
        moreIcon.setImageResource(currentItem.getmMoreIcon());


        return listItemView;
    }
}
