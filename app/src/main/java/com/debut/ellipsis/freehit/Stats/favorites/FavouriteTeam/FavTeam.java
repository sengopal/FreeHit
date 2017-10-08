package com.debut.ellipsis.freehit.Stats.favorites.FavouriteTeam;


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

import com.debut.ellipsis.freehit.R;
import com.debut.ellipsis.freehit.Stats.MoreMain.MoreItem;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity.MY_PREFS_NAME;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavTeam extends Fragment {


    public FavTeam() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_fav_team, container, false);
        final ArrayList<MoreItem> favTeamItem = new ArrayList<MoreItem>();
        favTeamItem.add(new MoreItem(R.drawable.arrow, R.string.category_news));
        favTeamItem.add(new MoreItem(R.drawable.arrow, R.string.upcoming_list));
        favTeamItem.add(new MoreItem(R.drawable.arrow, R.string.past_list));
        favTeamItem.add(new MoreItem(R.drawable.arrow, R.string.players));


        SharedPreferences prefs = getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String Countryname = prefs.getString("country_name", "null");


        FavTeamAdapter adapter = new FavTeamAdapter(getActivity(), favTeamItem);
        final ListView listView = (ListView) rootView.findViewById(R.id.fav_team_list);
        listView.setAdapter(adapter);
        listView.setClickable(true);

        if(Countryname.equals("null"))
        {
            listView.setOnItemClickListener(new ListView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getContext(),"Select A Favourite Team First",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            listView.setOnItemClickListener(new ListView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        Intent FavTeamNewsIntent = new Intent(getActivity(), FavTeamNews.class);
                        FavTeamNewsIntent.putExtra("CountryName",Countryname);
                        startActivity(FavTeamNewsIntent);

                    } else if (position == 1) {
                        Intent FavTeamUpcomingIntent = new Intent(getActivity(), FavTeamUpcomingMatches.class);
                        FavTeamUpcomingIntent.putExtra("CountryName",Countryname);
                        startActivity(FavTeamUpcomingIntent);

                    } else if (position == 2) {
                        Intent FavTeamPastIntent = new Intent(getActivity(), FavTeamPastMatches.class);
                        FavTeamPastIntent.putExtra("CountryName",Countryname);
                        startActivity(FavTeamPastIntent);

                    } else if (position == 3) {
                        Intent FavTeamPastIntent = new Intent(getActivity(), FavTeamPlayers.class);
                        FavTeamPastIntent.putExtra("CountryName",Countryname);
                        startActivity(FavTeamPastIntent);

                    }
                }
            });
        }
        return rootView;
    }

}
