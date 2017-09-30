package com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardElements;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreCardFragment extends Fragment {

    private ScoreCardExpandableListAdapter ExpAdapter;
    private ArrayList<ScoreCardItemListItem> ExpListItems;
    private ExpandableListView ExpandList;

    public ScoreCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent i = getActivity().getIntent();
        Toast.makeText(this.getActivity(), i.getStringExtra("match_id"), Toast.LENGTH_SHORT).show();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_matchscorecard_scorecard, container, false);

        ExpandList = (ExpandableListView) rootView.findViewById(R.id.expandable_match_scoreCard_batting);
        ExpListItems = ScoreCardExpandableListDataPump.getData();
        ExpAdapter = new ScoreCardExpandableListAdapter(getActivity(), ExpListItems);
        ExpandList.setAdapter(ExpAdapter);
        setExpandableListViewHeight(ExpandList, -1);
        ExpandList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {


            //THIS IS WHERE YOU HAVE TO CALL THE QUERY UTIL CLASS
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getContext(), " List Expanded.", Toast.LENGTH_SHORT).show();
            }
        });


        // WHEN LIST WILL BE COLLAPSED , No USE FOR US , SHALL BE REMOVED LATER WHILE PARSING
        ExpandList.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getContext(), " List Collapsed.", Toast.LENGTH_SHORT).show();

            }
        });


        // FOR CHILD DATA OnClick
        ExpandList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getContext(), "CHILD CLICKED", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return rootView;
    }

    // To control size of expandable listview based upon number of main list items
    // we'll discuss this feature after the live demo
    private void setExpandableListViewHeight(ExpandableListView listView, int group) {
        ExpandableListAdapter listAdapter = listView.getExpandableListAdapter();
        if (listAdapter == null) {
            return;
        }

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            view = listAdapter.getGroupView(i, false, view, listView);
            if (i == 0) {
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
            if (((listView.isGroupExpanded(i)) && (i != group)) || ((!listView.isGroupExpanded(i)) && (i == group))) {
                View listItem = null;
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    listItem = listAdapter.getChildView(i, j, false, listItem, listView);
                    listItem.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, View.MeasureSpec.UNSPECIFIED));
                    listItem.measure(
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                    totalHeight += listItem.getMeasuredHeight();
                }
            }
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

}
