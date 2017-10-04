package com.debut.ellipsis.freehit.Stats.Team;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchCardItem;
import com.debut.ellipsis.freehit.Matches.UpcomingMatches.UpcomingMatchListAdapter;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TeamSchedule extends Fragment {
    private TabLayout tabLayout;
    APIInterface apiInterface;
    private UpcomingMatchListAdapter MatchListAdapter;
    private ProgressBar mProgressBar;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_matches_complete_match_list, container, false);

        apiInterface = ApiClient.getClient().create(APIInterface.class);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        rv = (RecyclerView) rootView.findViewById(R.id.match_list);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));



        /**
         GET List Users
         **/
        Call<UpcomingMatchCardItem> call = apiInterface.doGetUpcomingCompleteMatchListResources();
        call.enqueue(new Callback<UpcomingMatchCardItem>() {
            @Override
            public void onResponse(Call<UpcomingMatchCardItem> call, Response<UpcomingMatchCardItem> response) {

                List<UpcomingMatchCardItem> upcomingMatchesList = response.body().getResults();
                mProgressBar.setVisibility(View.GONE);

                MatchListAdapter = new UpcomingMatchListAdapter(getContext(), upcomingMatchesList);
                rv.setAdapter(MatchListAdapter);
            }

            @Override
            public void onFailure(Call<UpcomingMatchCardItem> call, Throwable t) {
                call.cancel();
            }
        });




        return rootView;
    }
}
