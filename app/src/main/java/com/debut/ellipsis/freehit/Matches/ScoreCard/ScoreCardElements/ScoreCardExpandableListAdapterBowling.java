package com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardElements;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;

public class ScoreCardExpandableListAdapterBowling extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<ScoreCardItemListBowling> groups;

    public ScoreCardExpandableListAdapterBowling(Context context, ArrayList<ScoreCardItemListBowling> groups) {
        this.context = context;
        this.groups = groups;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<ScoreCardItemBowling> chList = groups.get(groupPosition).getItems();
        return chList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        ScoreCardItemBowling currentPlayer = (ScoreCardItemBowling) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.fragment_matchscorecard_title_list_item_bowling, null);
        }

        TextView BowlerName = (TextView) convertView.findViewById(R.id.bowler_name);
        BowlerName.setText(currentPlayer.getmBowlerName());

        TextView Wickets = (TextView) convertView.findViewById(R.id.WicketsValue);
        Wickets.setText(currentPlayer.getmWickets());

        TextView BowlerRuns = (TextView) convertView.findViewById(R.id.BowlerRunsValue);
        BowlerRuns.setText(currentPlayer.getmBowlerRuns());

        TextView EconomyRate = (TextView) convertView.findViewById(R.id.EconomyRateValue);
        EconomyRate.setText(currentPlayer.getmEconomyRate());

        TextView Overs = (TextView) convertView.findViewById(R.id.OversValue);
        Overs.setText(currentPlayer.getmOvers());

        TextView Maiden = (TextView) convertView.findViewById(R.id.MaidensValue);
        Maiden.setText(currentPlayer.getmMaidens());

        TextView NoBalls = (TextView) convertView.findViewById(R.id.NoBallsValue);
        NoBalls.setText(currentPlayer.getmNoBalls());

        TextView Wides = (TextView) convertView.findViewById(R.id.WidesValue);
        Wides.setText(currentPlayer.getmWides());

        TextView Dots = (TextView) convertView.findViewById(R.id.DotsValue);
        Dots.setText(currentPlayer.getmDots());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<ScoreCardItemBowling> chList = groups.get(groupPosition).getItems();
        return chList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        ScoreCardItemListBowling group = (ScoreCardItemListBowling) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.fragment_matchscorecard_title_list_group, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.Scorecard_team_name);
        tv.setText(group.getName());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
