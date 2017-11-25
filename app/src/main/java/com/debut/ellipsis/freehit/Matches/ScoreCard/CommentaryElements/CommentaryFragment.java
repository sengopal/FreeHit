package com.debut.ellipsis.freehit.Matches.ScoreCard.CommentaryElements;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentaryFragment extends Fragment {
    private List<String> commentaryItems;
    public static CommentaryItem commentaryItem;
    private CommentaryAdapter commentaryAdapter;
    private ProgressBar mProgressBar;
    private List<String> preLoad;
    private TextView noDataConnection;
    private RecyclerView recyclerView;

    public CommentaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES ? inflater.inflate(R.layout.fragment_matchscorecard_commentary_dark, container, false) : inflater.inflate(R.layout.fragment_matchscorecard_commentary, container, false);

        String match_id = getActivity().getIntent().getStringExtra("match_id");
        String team_1 = getActivity().getIntent().getStringExtra("team1");
        String team_2 = getActivity().getIntent().getStringExtra("team2");

        if(team_1.equals("SCO")||team_2.equals("SCO")||team_1.equals("PNG")||team_2.equals("PNG")||team_1.equals("HK")||team_2.equals("HK")||team_1.equals("UAE")||team_2.equals("UAE")||team_1.equals("NED")||team_2.equals("NED")||team_1.equals("OMN")||team_2.equals("OMN"))
            noDataConnection.setText(R.string.no_internet_connection);
        else {
            View viewProgress = rootView.findViewById(R.id.progress);
            mProgressBar = viewProgress.findViewById(R.id.progress_bar);

            noDataConnection = rootView.findViewById(R.id.no_data_no_connection);

            recyclerView = rootView.findViewById(R.id.recycler_view);

            preLoad = new ArrayList<>();

            Call<CommentaryItem> call = MainActivity.apiInterface.doGetLiveMatchCommentary(match_id);
            call.enqueue(new Callback<CommentaryItem>() {
                @Override
                public void onResponse(Call<CommentaryItem> call1, Response<CommentaryItem> response) {
                    if (response.isSuccessful()) {
                        mProgressBar.setVisibility(View.INVISIBLE);
                        commentaryItem = response.body();
                        commentaryItems = commentaryItem.getCommentary();

                        if (commentaryItems.size() > 20) {
                            for (int i = 0; i < 10; i++) {
                                preLoad.add(commentaryItems.get(i));
                            }
                        } else {
                            for (int i = 0; i < commentaryItems.size(); i++) {
                                preLoad.add(commentaryItems.get(i));
                            }
                        }


                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        commentaryAdapter = new CommentaryAdapter(recyclerView, preLoad, getActivity());
                        recyclerView.setAdapter(commentaryAdapter);

                        //set load more listener for the RecyclerView adapter
                        commentaryAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                            @Override
                            public void onLoadMore() {
                                if (preLoad.size() <= commentaryItems.size()) {
                                    preLoad.add(null);
                                    commentaryAdapter.notifyItemInserted(preLoad.size() - 1);
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            preLoad.remove(preLoad.size() - 1);
                                            commentaryAdapter.notifyItemRemoved(preLoad.size());
                                            //Generating more data
                                            int index = preLoad.size();
                                            int end;
                                            if (index + 10 > commentaryItems.size()) {
                                                end = commentaryItems.size();
                                            } else {
                                                end = index + 10;
                                            }
                                            for (int i = index; i < end; i++) {
                                                preLoad.add(commentaryItems.get(i));
                                            }
                                            commentaryAdapter.notifyDataSetChanged();
                                            commentaryAdapter.setLoaded();
                                        }
                                    }, 500);
                                }

                            }
                        });

                    } else {
                        noDataConnection.setText("Servers are Down ,we'll be back up in a while");
                        mProgressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), R.string.server_issues, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CommentaryItem> call1, Throwable t) {
                    mProgressBar.setVisibility(View.GONE);
                    call1.cancel();
                    noDataConnection.setText(R.string.no_internet_connection);
                }
            });
        }

        return rootView;
    }

}
