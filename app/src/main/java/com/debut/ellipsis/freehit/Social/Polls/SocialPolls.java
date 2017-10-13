package com.debut.ellipsis.freehit.Social.Polls;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.MainActivity;
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
    private ProgressBar mProgressBar;
    RelativeLayout rlcontainer;
    public TextView NoPollsText;
    public Button NoPollsButton;
    public Button NoConnectionButton;

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

        final SwipeRefreshLayout refLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh_layout);

        final View No_polls = rootView.findViewById(R.id.No_news);

        NoPollsText = (TextView) No_polls.findViewById(R.id.empty_view);
        NoPollsButton = (Button) No_polls.findViewById(R.id.No_Live_Matches_button);

        final View no_internet_connection = rootView.findViewById(R.id.Unavailable_connection);
        
        NoConnectionButton = (Button) no_internet_connection.findViewById(R.id.no_internet_refresh_button);


        /**
         GET Polls List Resources
         **/
        final Call<PollCardItem> call = apiInterface.doGetPollsListResources();
        call.enqueue(new Callback<PollCardItem>() {
            @Override
            public void onResponse(Call<PollCardItem> call, Response<PollCardItem> response) {
                mProgressBar.setVisibility(View.INVISIBLE);
                if (getActivity() != null) {

                    List<PollCardItem> polls = response.body().getResults();
                    if (polls.size() == 0) {

                        No_polls.setVisibility(View.VISIBLE);
                        NoPollsText.setText(R.string.EmptyPolls);
                        NoPollsButton.setOnClickListener(new View.OnClickListener() {

                            public void onClick(View v) {
                                Intent i = new Intent(getContext(), MainActivity.class);//which is your mainActivity-Launcher
                                i.putExtra("Main_tab",2);
                                i.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(i);
                            }
                        });

                    }
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setAdapter(new PollItemAdapter(polls, R.layout.fragment_social_polls_list_item, getContext()));
                }

            }

            @Override
            public void onFailure(Call<PollCardItem> call, Throwable t) {

                mProgressBar.setVisibility(View.GONE);
                no_internet_connection.setVisibility(View.VISIBLE);
                NoConnectionButton.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        Intent i = new Intent(getContext(), MainActivity.class);//which is your mainActivity-Launcher
                        i.putExtra("Main_tab",2);
                        i.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);

                    }
                });
                call.cancel();
            }
        });
        refLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);
        refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                           @Override
                                           public void onRefresh() {
                                               // Checking if connected or not on refresh
                                               refLayout.setRefreshing(true);

                                               Call<PollCardItem> call = apiInterface.doGetPollsListResources();
                                               call.enqueue(new Callback<PollCardItem>() {
                                                   @Override
                                                   public void onResponse(Call<PollCardItem> call, Response<PollCardItem> response) {
                                                       mProgressBar.setVisibility(View.INVISIBLE);
                                                       no_internet_connection.setVisibility(View.INVISIBLE);
                                                       No_polls.setVisibility(View.INVISIBLE);
                                                       if (getActivity() != null) {

                                                           List<PollCardItem> polls = response.body().getResults();
                                                           if (polls.size() == 0) {
                                                               No_polls.setVisibility(View.VISIBLE);

                                                               NoPollsText.setText(R.string.EmptyPolls);
                                                               NoPollsButton.setOnClickListener(new View.OnClickListener() {

                                                                   public void onClick(View v) {
                                                                       Intent i = new Intent(getContext(), MainActivity.class);//which is your mainActivity-Launcher
                                                                       i.putExtra("Main_tab",2);
                                                                       i.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                                                                       i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                                       startActivity(i);
                                                                   }
                                                               });
                                                           }
                                                           recyclerView.setVisibility(View.VISIBLE);
                                                           recyclerView.setAdapter(new PollItemAdapter(polls, R.layout.fragment_social_polls_list_item, getContext()));
                                                       }
                                                   }

                                                   @Override
                                                   public void onFailure(Call<PollCardItem> call, Throwable t) {
                                                       mProgressBar.setVisibility(View.INVISIBLE);
                                                       mProgressBar.setVisibility(View.INVISIBLE);
                                                       Toast toast=Toast.makeText(getContext(),R.string.no_internet_connection,Toast.LENGTH_SHORT);
                                                       toast.show();
                                                       call.cancel();
                                                   }
                                               });
                                               refLayout.setRefreshing(false);
                                           }


                                       }
        );

        return rootView;
    }

}
