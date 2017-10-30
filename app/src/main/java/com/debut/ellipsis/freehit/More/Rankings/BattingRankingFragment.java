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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BattingRankingFragment extends Fragment {

    private RecyclerView rv;
    private ProgressBar mProgressBar;
    private Spinner team_format;
    private LinearLayoutManager mLinearLayoutManager;

    public BattingRankingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_ranking_batting, container, false);

        View viewRecycler = rootView.findViewById(R.id.team_list);
        rv = (RecyclerView) viewRecycler.findViewById(R.id.recycler_list);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        rv.setLayoutManager(mLinearLayoutManager);

        View teamSpinner = rootView.findViewById(R.id.team_format_select);
        team_format = (Spinner) teamSpinner.findViewById(R.id.spinner);

        View viewProgress = rootView.findViewById(R.id.progress);
        mProgressBar = (ProgressBar) viewProgress.findViewById(R.id.progress_bar);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.format_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        team_format.setAdapter(adapter);

        Call<RankingItem> call = MainActivity.apiInterface.doGetRankingResources();
        call.enqueue(new Callback<RankingItem>() {
            @Override
            public void onResponse(Call<RankingItem> call, Response<RankingItem> response) {

                final List<RankingItem> teamList = response.body().getResults();
                System.out.println("going into on response with size "+teamList.size());
                mProgressBar.setVisibility(View.GONE);
                System.out.println("TEST "+teamList.get(0).getOdi().getTeamList().get(0).getTeam());
                rv.setAdapter(new PlayerRankingAdapter(getContext(), teamList.get(0).getOdi().getBattingList()));
                team_format.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        rv.setAdapter(null);
                        final String selectedItem = parent.getItemAtPosition(position).toString();
                        System.out.println("going here");
                        if(selectedItem.equals("ODI"))
                            rv.setAdapter(new PlayerRankingAdapter(getContext(), teamList.get(0).getOdi().getBattingList()));
                        else if(selectedItem.equals("T20"))
                            rv.setAdapter(new PlayerRankingAdapter(getContext(), teamList.get(0).getT20().getBattingList()));
                        else if(selectedItem.equals("TEST"))
                            rv.setAdapter(new PlayerRankingAdapter(getContext(), teamList.get(0).getTest().getBattingList()));
                    } // to close the onItemSelected
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });
            }

            @Override
            public void onFailure(Call<RankingItem> call, Throwable t) {
                mProgressBar.setVisibility(View.INVISIBLE);
                Toast toast=Toast.makeText(getContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT);
                toast.show();
                call.cancel();
            }
        });


        return rootView;
    }

}
