package com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardElements;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.debut.ellipsis.freehit.Matches.LiveMatches.LiveMatchScoreCard;
import com.debut.ellipsis.freehit.Matches.PastMatches.PastMatchScoreCard;
import com.debut.ellipsis.freehit.Matches.ScoreCard.ScoreCardItem;
import com.debut.ellipsis.freehit.R;

import java.util.List;

public class Innings1_Innings2 extends Fragment {

    ScoreCardBattingAdapter mBattingAdapter;
    ScoreCardBowlingAdapter mBowlingAdapter;
    ScoreCardFOWAdapter mFowAdapter;
    private List<ScoreCardItem> teamList = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String fragment_name = getArguments().getString("fragment_name");
        String match_type = getActivity().getIntent().getStringExtra("match_type");

        if (match_type.equals("PAST")) {
            teamList = ((PastMatchScoreCard) getActivity()).getQList();
        }else if(match_type.equals("LIVE")) {
            teamList = LiveMatchScoreCard.getQList();
        }

        View rootView = null ;

        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_YES:
                rootView = inflater.inflate(R.layout.fragment_matchscorecard_scorecard_innings_dark, container, false);
                break;
            case AppCompatDelegate.MODE_NIGHT_NO:
                rootView = inflater.inflate(R.layout.fragment_matchscorecard_scorecard_innings, container, false);
                break;
        }

        ListView listViewBatting = rootView.findViewById(R.id.batting_list);

        ListView listViewBowling = rootView.findViewById(R.id.bowling_list);

        ListView listViewFow = rootView.findViewById(R.id.fow_list);

        TextView extras = rootView.findViewById(R.id.extras);
        TextView TotalScore = rootView.findViewById(R.id.TotalScore);
        String Extras ;

        switch (fragment_name) {
            case "Team_1_Innings_1":
                mBattingAdapter = new ScoreCardBattingAdapter(getContext(), teamList.get(0).getScorecard().getTeam2().getFirstinn().getBatting());
                listViewBatting.setAdapter(mBattingAdapter);
                mBattingAdapter.notifyDataSetChanged();
                justifyListViewHeightBasedOnChildren(listViewBatting);
                Extras = teamList.get(0).getScorecard().getTeam2().getFirstinn().getExtras();
                extras.setText(Extras);
                TotalScore.setText(teamList.get(0).getScorecard().getTeam2().getFirstinn().getScore());

                mBowlingAdapter = new ScoreCardBowlingAdapter(getContext(), teamList.get(0).getScorecard().getTeam2().getFirstinn().getBowling());
                listViewBowling.setAdapter(mBowlingAdapter);
                mBowlingAdapter.notifyDataSetChanged();
                justifyListViewHeightBasedOnChildren(listViewBowling);

                mFowAdapter = new ScoreCardFOWAdapter(getContext(), teamList.get(0).getScorecard().getTeam2().getFirstinn().getFow());
                listViewFow.setAdapter(mFowAdapter);
                mFowAdapter.notifyDataSetChanged();
                justifyListViewHeightBasedOnChildren(listViewFow);

                break;
            case "Team_2_Innings_1":
                mBattingAdapter = new ScoreCardBattingAdapter(getContext(), teamList.get(0).getScorecard().getTeam1().getFirstinn().getBatting());
                listViewBatting.setAdapter(mBattingAdapter);
                mBattingAdapter.notifyDataSetChanged();
                justifyListViewHeightBasedOnChildren(listViewBatting);
                Extras = teamList.get(0).getScorecard().getTeam1().getFirstinn().getExtras();
                extras.setText(Extras);
                TotalScore.setText(teamList.get(0).getScorecard().getTeam1().getFirstinn().getScore());

                mBowlingAdapter = new ScoreCardBowlingAdapter(getContext(), teamList.get(0).getScorecard().getTeam1().getFirstinn().getBowling());
                listViewBowling.setAdapter(mBowlingAdapter);
                mBowlingAdapter.notifyDataSetChanged();
                justifyListViewHeightBasedOnChildren(listViewBowling);

                mFowAdapter = new ScoreCardFOWAdapter(getContext(), teamList.get(0).getScorecard().getTeam1().getFirstinn().getFow());
                listViewFow.setAdapter(mFowAdapter);
                mFowAdapter.notifyDataSetChanged();
                justifyListViewHeightBasedOnChildren(listViewFow);
                break;
            case "Team_1_Innings_2":
                mBattingAdapter = new ScoreCardBattingAdapter(getContext(), teamList.get(0).getScorecard().getTeam2().getSecondinn().getBatting());
                listViewBatting.setAdapter(mBattingAdapter);
                mBattingAdapter.notifyDataSetChanged();
                justifyListViewHeightBasedOnChildren(listViewBatting);
                Extras = teamList.get(0).getScorecard().getTeam2().getSecondinn().getExtras();
                extras.setText(Extras);
                TotalScore.setText(teamList.get(0).getScorecard().getTeam2().getSecondinn().getScore());

                mBowlingAdapter = new ScoreCardBowlingAdapter(getContext(), teamList.get(0).getScorecard().getTeam2().getSecondinn().getBowling());
                listViewBowling.setAdapter(mBowlingAdapter);
                mBowlingAdapter.notifyDataSetChanged();
                justifyListViewHeightBasedOnChildren(listViewBowling);

                mFowAdapter = new ScoreCardFOWAdapter(getContext(), teamList.get(0).getScorecard().getTeam2().getSecondinn().getFow());
                listViewFow.setAdapter(mFowAdapter);
                mFowAdapter.notifyDataSetChanged();
                justifyListViewHeightBasedOnChildren(listViewFow);

                break;
            case "Team_2_Innings_2":
                mBattingAdapter = new ScoreCardBattingAdapter(getContext(), teamList.get(0).getScorecard().getTeam1().getSecondinn().getBatting());
                listViewBatting.setAdapter(mBattingAdapter);
                mBattingAdapter.notifyDataSetChanged();
                justifyListViewHeightBasedOnChildren(listViewBatting);
                Extras = teamList.get(0).getScorecard().getTeam1().getFirstinn().getExtras();
                extras.setText(Extras);
                TotalScore.setText(teamList.get(0).getScorecard().getTeam1().getSecondinn().getScore());

                mBowlingAdapter = new ScoreCardBowlingAdapter(getContext(), teamList.get(0).getScorecard().getTeam1().getSecondinn().getBowling());
                listViewBowling.setAdapter(mBowlingAdapter);
                mBowlingAdapter.notifyDataSetChanged();
                justifyListViewHeightBasedOnChildren(listViewBowling);

                mFowAdapter = new ScoreCardFOWAdapter(getContext(), teamList.get(0).getScorecard().getTeam1().getSecondinn().getFow());
                listViewFow.setAdapter(mFowAdapter);
                mFowAdapter.notifyDataSetChanged();
                justifyListViewHeightBasedOnChildren(listViewFow);

                break;
        }

        return rootView;
    }

    public void justifyListViewHeightBasedOnChildren (ListView listView) {

        ListAdapter adapter = listView.getAdapter();

        if (adapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
    }
}
