package com.debut.ellipsis.freehit.More.MoreMain;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.More.Player.PlayerSearchActivity;
import com.debut.ellipsis.freehit.More.Rankings.RankingActivity;
import com.debut.ellipsis.freehit.More.Series.SeriesMainActivity;
import com.debut.ellipsis.freehit.More.Team.TeamActivity;
import com.debut.ellipsis.freehit.More.Team.TeamListView;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity.MY_PREFS_NAME;

public class MoreFragment extends Fragment {


    public MoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.common_list, container, false);

        SharedPreferences prefs = getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String CountryName = prefs.getString("country_name", "null");

        final ArrayList<MoreItem> moreItem = new ArrayList<MoreItem>();

        moreItem.add(new MoreItem(R.drawable.player_vector, R.string.search_player));
        moreItem.add(new MoreItem(R.drawable.team_vector, R.string.search_team));
        moreItem.add(new MoreItem(R.drawable.series_vector, R.string.search_series));
        moreItem.add(new MoreItem(R.drawable.fav_vector, R.string.fav_team));
        moreItem.add(new MoreItem(R.drawable.ranking, R.string.rankings));

        MoreAdapter adapter = new MoreAdapter(getActivity(), moreItem);
        final ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent PlayerIntent = new Intent(getActivity(), PlayerSearchActivity.class);
                    startActivity(PlayerIntent);

                } else if (position == 1) {
                    Intent TeamIntent = new Intent(getActivity(), TeamListView.class);
                    startActivity(TeamIntent);

                } else if (position == 2) {
                    Intent SeriesIntent = new Intent(getActivity(), SeriesMainActivity.class);
                    startActivity(SeriesIntent);

                }
                else if (position == 3) {
                    if(CountryName.equals("null")) {
                        Toast.makeText(getContext(),"Select A Favourite Team First",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent FavouritesIntent = new Intent(getActivity(), TeamActivity.class);
                        FavouritesIntent.putExtra("fav_country", CountryName);
                        startActivity(FavouritesIntent);
                    }

                }
                else if (position==4){
                    Intent RankingIntent = new Intent(getActivity(), RankingActivity.class);
                    startActivity(RankingIntent);
                }

            }
        });


        return rootView;

    }

}