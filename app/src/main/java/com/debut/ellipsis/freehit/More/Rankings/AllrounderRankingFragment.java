package com.debut.ellipsis.freehit.More.Rankings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class AllrounderRankingFragment extends Fragment {

    private RecyclerView rv;
    private Spinner team_format;
    private LinearLayoutManager mLinearLayoutManager;

    public AllrounderRankingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_more_ranking_batting, container, false);

        rv = (RecyclerView) rootView.findViewById(R.id.recycler_list);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        rv.setLayoutManager(mLinearLayoutManager);

        View teamSpinner = rootView.findViewById(R.id.team_format_select);
        team_format = (Spinner) teamSpinner.findViewById(R.id.spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.format_array, R.layout.spinner_item_selected);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        team_format.setAdapter(adapter);
                final List<RankingItem> teamList = ((RankingActivity)getActivity()).getQList();

                rv.setAdapter(new PlayerRankingAdapter(getContext(), teamList.get(0).getOdi().getAllRounders()));
                team_format.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        rv.setAdapter(null);
                        final String selectedItem = parent.getItemAtPosition(position).toString();

                        if(selectedItem.equals("ODI"))
                            rv.setAdapter(new PlayerRankingAdapter(getContext(), teamList.get(0).getOdi().getAllRounders()));
                        else if(selectedItem.equals("T20"))
                            rv.setAdapter(new PlayerRankingAdapter(getContext(), teamList.get(0).getT20().getAllRounders()));
                        else if(selectedItem.equals("TEST"))
                            rv.setAdapter(new PlayerRankingAdapter(getContext(), teamList.get(0).getTest().getAllRounders()));
                    } // to close the onItemSelected
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }

        });

        return rootView;
    }

}
