package com.debut.ellipsis.freehit.Social.Polls;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SocialPolls extends Fragment {

    APIInterface apiInterface;
    private PollItemAdapter mAdapter;
    private ProgressBar mProgressBar;

    public SocialPolls() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_list, container, false);

        apiInterface = ApiClient.getClient().create(APIInterface.class);

        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.news_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);

        /**
         GET List Resources
         **/
        Call<PollCardItem> call = apiInterface.doGetPollsListResources();
        call.enqueue(new Callback<PollCardItem>() {
            @Override
            public void onResponse(Call<PollCardItem> call, Response<PollCardItem> response) {

                mProgressBar.setVisibility(View.INVISIBLE);


                List<PollCardItem> polls = response.body().getResults();

                recyclerView.setAdapter(new PollItemAdapter(polls, R.layout.fragment_social_polls_list_item, getContext()));
            }

            @Override
            public void onFailure(Call<PollCardItem> call, Throwable t) {

                Toast.makeText(getContext(), "Error: " + t.toString(), Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });


        return rootView;
    }

}
