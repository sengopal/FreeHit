package com.debut.ellipsis.freehit.Matches.ScoreCard;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ScoreCardExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<ScoreCardItemListItem> groups;

    public ScoreCardExpandableListAdapter(Context context, ArrayList<ScoreCardItemListItem> groups) {
        this.context = context;
        this.groups = groups;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<ScoreCardItem> chList = groups.get(groupPosition).getItems();
        return chList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        ScoreCardItem currentPlayer = (ScoreCardItem) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.fragment_matchscorecard_title_list_item, null);
        }

        TextView PlayerName = (TextView) convertView.findViewById(R.id.Player_name);
        PlayerName.setText(currentPlayer.getmPlayerName());

        TextView PlayerOutType = (TextView) convertView.findViewById(R.id.player_out_type);
        PlayerOutType.setText(currentPlayer.getmPlayerOutType());

        TextView NumberOfFours = (TextView) convertView.findViewById(R.id.FourValue);
        NumberOfFours.setText(currentPlayer.getmNumberOfFours());

        TextView NumberOfSixes = (TextView) convertView.findViewById(R.id.SixValue);
        NumberOfSixes.setText(currentPlayer.getmNumberOfSixes());

        TextView NumberOfDots= (TextView) convertView.findViewById(R.id.DotValue);
        NumberOfDots.setText(currentPlayer.getmNumberOfDots());

        TextView NumberOfRuns= (TextView) convertView.findViewById(R.id.RunsValue);
        NumberOfRuns.setText(currentPlayer.getmRuns());

        TextView NumberOfBalls= (TextView) convertView.findViewById(R.id.BallsValue);
        NumberOfBalls.setText(currentPlayer.getmBalls());

        TextView StrikeRate= (TextView) convertView.findViewById(R.id.StrikeRateValue);
        StrikeRate.setText(currentPlayer.getmStrikeRate());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<ScoreCardItem> chList = groups.get(groupPosition).getItems();
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
        ScoreCardItemListItem group = (ScoreCardItemListItem) getGroup(groupPosition);
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