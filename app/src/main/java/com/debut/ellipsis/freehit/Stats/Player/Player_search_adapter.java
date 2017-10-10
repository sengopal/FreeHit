package com.debut.ellipsis.freehit.Stats.Player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;

/**
 * Created by Jayanth on 09-10-2017.
 */

public class Player_search_adapter extends ArrayAdapter<Player_search_item> {
public Player_search_adapter(Context context, ArrayList<Player_search_item> items) {

        super(context, 0, items);
        }

public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
        listItemView = LayoutInflater.from(getContext()).inflate(R.layout.stats_player_search_item_list, parent, false);
        }

        Player_search_item currentItem = getItem(position);


        TextView statsNameTextView = (TextView) listItemView.findViewById(R.id.row_title);
        statsNameTextView.setText(currentItem.getmPlayerName());



        return listItemView;
        }


        }
