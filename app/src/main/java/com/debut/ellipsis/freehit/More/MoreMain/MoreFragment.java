package com.debut.ellipsis.freehit.More.MoreMain;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.debut.ellipsis.freehit.More.Player.PlayerSearch_Fragment;
import com.debut.ellipsis.freehit.More.Rankings.RankingActivity;
import com.debut.ellipsis.freehit.More.Records.RecordsActivity;
import com.debut.ellipsis.freehit.More.Series.SeriesListView;
import com.debut.ellipsis.freehit.More.Team.TeamListView;
import com.debut.ellipsis.freehit.More.favorites.FavoritesActivity;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class
MoreFragment extends Fragment {


    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_more_list, container, false);

        final ArrayList<MoreItem> moreItem = new ArrayList<MoreItem>();

        moreItem.add(new MoreItem(R.drawable.player, R.string.search_player));
        moreItem.add(new MoreItem(R.drawable.team, R.string.search_team));
        moreItem.add(new MoreItem(R.drawable.shield, R.string.search_series));
        moreItem.add(new MoreItem(R.drawable.fav, R.string.fav));
        moreItem.add(new MoreItem(R.drawable.rankings, R.string.rankings));
        moreItem.add(new MoreItem(R.drawable.records, R.string.records));



        MoreAdapter adapter = new MoreAdapter(getActivity(), moreItem);
        final ListView listView = (ListView) rootView.findViewById(R.id.stats_list);
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent PlayerIntent = new Intent(getActivity(), PlayerSearch_Fragment.class);
                    startActivity(PlayerIntent);

                } else if (position == 1) {
                    Intent TeamIntent = new Intent(getActivity(), TeamListView.class);
                    startActivity(TeamIntent);

                } else if (position == 2) {
                    Intent SeriesIntent = new Intent(getActivity(), SeriesListView.class);
                    startActivity(SeriesIntent);

                }
                else if (position == 3) {
                    Intent RecordsIntent = new Intent(getActivity(), FavoritesActivity.class);
                    startActivity(RecordsIntent);

                }
                else if (position==4){
                    Intent RankingIntent = new Intent(getActivity(), RankingActivity.class);
                    startActivity(RankingIntent);
                    getActivity().overridePendingTransition(0,0);

                }
                else if (position == 5) {
                    Intent RecordsIntent = new Intent(getActivity(), RecordsActivity.class);
                    startActivity(RecordsIntent);

                }

            }
        });


        return rootView;

    }

}