package com.debut.ellipsis.freehit.Matches.ScoreCard.CommentaryElements;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.debut.ellipsis.freehit.Matches.LiveMatches.LiveMatchScoreCard;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

public class CommentaryFragment extends Fragment {
    private List<String> commentaryItems;
    private CommentaryAdapter commentaryAdapter;


    public CommentaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_matchscorecard_commentary, container, false);
        String match_id = getActivity().getIntent().getStringExtra("match_id");
        System.out.println(match_id);
        CommentaryItem commentaryItem = LiveMatchScoreCard.getCQList();
        commentaryItems = commentaryItem.getCommentary();

        List<String> preLoad = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            preLoad.add(commentaryItems.get(i));
        }

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        commentaryAdapter = new CommentaryAdapter(recyclerView, preLoad, getActivity());
        recyclerView.setAdapter(commentaryAdapter);

        //set load more listener for the RecyclerView adapter
        commentaryAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                /*if (commentaryItems.size() <= 20) {*/
                    commentaryItems.add(null);
                    commentaryAdapter.notifyItemInserted(commentaryItems.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            commentaryItems.remove(commentaryItems.size() - 1);
                            commentaryAdapter.notifyItemRemoved(commentaryItems.size());
                            List<String> afterLoad = new ArrayList<>();
                            //Generating more data
                            int index = commentaryItems.size();
                            int end = index + 10;
                            for (int i = index; i < end; i++) {
                                afterLoad.add(commentaryItems.get(i));
                            }
                            commentaryAdapter.notifyDataSetChanged();
                            commentaryAdapter.setLoaded();
                        }
                    }, 1000);
                /*} else {
                    Toast.makeText(getContext(), "Loading data completed", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

        return rootView;
    }

}
