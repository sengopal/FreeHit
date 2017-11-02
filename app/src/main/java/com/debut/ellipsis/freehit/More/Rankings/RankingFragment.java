package com.debut.ellipsis.freehit.More.Rankings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.debut.ellipsis.freehit.R;

import java.util.List;

public class RankingFragment extends Fragment {

    private RecyclerView rv;

    public RankingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String fragment_name = getArguments().getString("fragment_name");
        // Inflate the layout for this fragment
        View rootView = null;


        final List<RankingItem> teamList = ((RankingActivity) getActivity()).getQList();

        Spinner team_format;
        LinearLayoutManager mLinearLayoutManager;
        if (fragment_name.equals("TEAMS")) {

            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                rootView = inflater.inflate(R.layout.fragment_more_ranking_team_dark, container, false);
            } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
                rootView = inflater.inflate(R.layout.fragment_more_ranking_team, container, false);
            }


            rv = rootView.findViewById(R.id.recycler_list);

            mLinearLayoutManager = new LinearLayoutManager(getActivity());

            rv.setLayoutManager(mLinearLayoutManager);

            View teamSpinner = rootView.findViewById(R.id.team_format_select);

            team_format = teamSpinner.findViewById(R.id.spinner);

            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.format_array, R.layout.spinner_item_selected);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            team_format.setAdapter(adapter);

            rv.setAdapter(new TeamRankingAdapter(getContext(), teamList.get(0).getOdi().getTeamList()));
            team_format.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    rv.setAdapter(null);
                    final String selectedItem = parent.getItemAtPosition(position).toString();

                    switch (selectedItem) {
                        case "ODI":
                            rv.setAdapter(new TeamRankingAdapter(getContext(), teamList.get(0).getOdi().getTeamList()));
                            break;
                        case "T20":
                            rv.setAdapter(new TeamRankingAdapter(getContext(), teamList.get(0).getT20().getTeamList()));
                            break;
                        case "TEST":
                            rv.setAdapter(new TeamRankingAdapter(getContext(), teamList.get(0).getTest().getTeamList()));
                            break;
                    }
                }

                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {

            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                rootView = inflater.inflate(R.layout.fragment_more_ranking_batting_dark, container, false);
            } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
                rootView = inflater.inflate(R.layout.fragment_more_ranking_batting, container, false);
            }

            rv = rootView.findViewById(R.id.recycler_list);

            mLinearLayoutManager = new LinearLayoutManager(getActivity());

            rv.setLayoutManager(mLinearLayoutManager);

            View teamSpinner = rootView.findViewById(R.id.team_format_select);
            team_format = teamSpinner.findViewById(R.id.spinner);

            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.format_array, R.layout.spinner_item_selected);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            team_format.setAdapter(adapter);

            if (fragment_name.equals("BATSMEN")) {
                rv.setAdapter(new PlayerRankingAdapter(getContext(), teamList.get(0).getOdi().getBattingList()));
                team_format.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        rv.setAdapter(null);
                        final String selectedItem = parent.getItemAtPosition(position).toString();

                        switch (selectedItem) {
                            case "ODI":
                                rv.setAdapter(new PlayerRankingAdapter(getContext(), teamList.get(0).getOdi().getBattingList()));
                                break;
                            case "T20":
                                rv.setAdapter(new PlayerRankingAdapter(getContext(), teamList.get(0).getT20().getBattingList()));
                                break;
                            case "TEST":
                                rv.setAdapter(new PlayerRankingAdapter(getContext(), teamList.get(0).getTest().getBattingList()));
                                break;
                        }
                    }

                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            if (fragment_name.equals("BOWLER")) {
                rv.setAdapter(new PlayerRankingAdapter(getContext(), teamList.get(0).getOdi().getAllRounders()));
                team_format.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        rv.setAdapter(null);
                        final String selectedItem = parent.getItemAtPosition(position).toString();

                        switch (selectedItem) {
                            case "ODI":
                                rv.setAdapter(new PlayerRankingAdapter(getContext(), teamList.get(0).getOdi().getBowlingList()));
                                break;
                            case "T20":
                                rv.setAdapter(new PlayerRankingAdapter(getContext(), teamList.get(0).getT20().getBowlingList()));
                                break;
                            case "TEST":
                                rv.setAdapter(new PlayerRankingAdapter(getContext(), teamList.get(0).getTest().getBowlingList()));
                                break;
                        }
                    } // to close the onItemSelected

                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            if (fragment_name.equals("ALL ROUNDER")) {
                rv.setAdapter(new PlayerRankingAdapter(getContext(), teamList.get(0).getOdi().getAllRounders()));
                team_format.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        rv.setAdapter(null);
                        final String selectedItem = parent.getItemAtPosition(position).toString();

                        switch (selectedItem) {
                            case "ODI":
                                rv.setAdapter(new PlayerRankingAdapter(getContext(), teamList.get(0).getOdi().getAllRounders()));
                                break;
                            case "T20":
                                rv.setAdapter(new PlayerRankingAdapter(getContext(), teamList.get(0).getT20().getAllRounders()));
                                break;
                            case "TEST":
                                rv.setAdapter(new PlayerRankingAdapter(getContext(), teamList.get(0).getTest().getAllRounders()));
                                break;
                        }
                    } // to close the onItemSelected

                    public void onNothingSelected(AdapterView<?> parent) {

                    }

                });
            }
        }

        return rootView;
    }

}
