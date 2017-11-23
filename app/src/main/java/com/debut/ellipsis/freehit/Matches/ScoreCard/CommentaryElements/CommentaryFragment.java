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


        CommentaryItem commentaryItem = LiveMatchScoreCard.getCQList();
        commentaryItems = commentaryItem.getCommentary();

        final List<String> preLoad = new ArrayList<>();

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

        return rootView;
    }

}
