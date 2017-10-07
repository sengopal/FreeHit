package com.debut.ellipsis.freehit.Stats.Player;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.News.NewsItemAdapter;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Info_Fragment extends Fragment {
    //ProgressBar that is displayed before the earthquake list is generated

    private NewsItemAdapter mAdapter;
    private ProgressBar mProgressBar;
    private String player_url;
    APIInterface apiInterface;

    public Info_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Intent i = getActivity().getIntent();
        player_url = i.getStringExtra("player_url");

        final View rootView = inflater.inflate(R.layout.fragment_stats_player_info, container, false);

        final TextView name = (TextView) rootView.findViewById(R.id.Player_name_stats_info);
        final TextView country = (TextView) rootView.findViewById(R.id.player_country);
        final TextView DOB = (TextView) rootView.findViewById(R.id.DOB);
        final TextView age = (TextView) rootView.findViewById(R.id.Age);
        final TextView BattingStyle = (TextView) rootView.findViewById(R.id.battingStyle);
        final TextView BowlingStyle = (TextView) rootView.findViewById((R.id.BowlingStyle));
        final TextView TeamsPlayed = (TextView) rootView.findViewById((R.id.Teams_Played));
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);

        apiInterface = ApiClient.getClient().create(APIInterface.class);


        //to make changes
        Call<InfoItem> call = apiInterface.doGetInfoResources(player_url);
        call.enqueue(new Callback<InfoItem>() {
            @Override
            public void onResponse(Call<InfoItem> call, Response<InfoItem> response) {

                InfoItem info = response.body();

                name.setText(info.getName());

                country.setText(info.getNationality());

                DOB.setText(info.getDob());

                age.setText(info.getAge());

                BattingStyle.setText(info.getBatstyle());

                BowlingStyle.setText(info.getBowlstyle());

                mProgressBar.setVisibility(View.GONE);

                TeamsPlayed.setText(info.getTeamsplayed());

                final ImageView articleImage = (ImageView) rootView.findViewById(R.id.Player_image);

                final String ImageURL = info.getImg();

                Glide.with(getContext()).load(ImageURL).centerCrop().placeholder(R.drawable.matches).into(articleImage);
                TextView odiBat = (TextView) rootView.findViewById(R.id.odibattingRanking);
                TextView worldBat = (TextView) rootView.findViewById(R.id.worldBattingRanking);
                TextView t20 = (TextView) rootView.findViewById(R.id.T20BattingRanking);

                List<String> batrank = info.getBatrank();
                for (int i = 0; i < batrank.size(); i++) {
                    String str = batrank.get(i);
                    String[] words = str.split("-");
                    if (i == 0) {
                        str = words[1].trim();
                        odiBat.setText(str);
                    } else if (i == 1) {
                        str = words[1].trim();
                        worldBat.setText(str);

                    } else if (i == 2) {
                        str = words[1].trim();
                        t20.setText(str);

                    }

                }


                List<String> bowl = info.getBowlrank();
                TextView odibowl = (TextView) rootView.findViewById(R.id.odiBowlingRanking);
                TextView worldbowl = (TextView) rootView.findViewById(R.id.worldBowlingRanking);
                TextView t20bowl = (TextView) rootView.findViewById(R.id.T20BowlingRanking);
                for (int i = 0; i < bowl.size(); i++) {
                    String str2 = bowl.get(i);
                    String[] words2 = str2.split("-");
                    if (i == 0) {
                        str2 = words2[1].trim();
                        odibowl.setText(str2);
                    } else if (i == 1) {
                        str2 = words2[1].trim();
                        worldbowl.setText(str2);

                    } else if (i == 2) {
                        str2 = words2[1].trim();
                        t20bowl.setText(str2);

                    }

                }

                TextView motest = (TextView) rootView.findViewById(R.id.MOTM_Test);
                TextView motodi = (TextView) rootView.findViewById(R.id.MOTM_Odi);
                TextView mot20 = (TextView) rootView.findViewById(R.id.MOTM_T20);
                TextView motwc = (TextView) rootView.findViewById(R.id.MOTM_WorldCup);
                TextView motipl = (TextView) rootView.findViewById(R.id.MOTM_IPL);
                TextView motcl = (TextView) rootView.findViewById(R.id.MOTM_CL);

                List<String> mom = info.getManofthematch();
                for (int i = 0; i < mom.size(); i++) {
                    String str3 = mom.get(i);
                    String[] words3 = str3.split("-");
                    switch (i) {
                        case 0:
                            str3 = words3[1].trim();
                            motest.setText(str3);
                            break;
                        case 1:
                            str3 = words3[1].trim();
                            motodi.setText(str3);
                            break;
                        case 2:
                            str3 = words3[1].trim();
                            mot20.setText(str3);
                            break;
                        case 3:
                            str3 = words3[1].trim();
                            motwc.setText(str3);
                            break;
                        case 4:
                            str3 = words3[1].trim();
                            motipl.setText(str3);
                            break;
                        case 5:
                            str3 = words3[1].trim();
                            motcl.setText(str3);
                            break;
                    }
                }

            }

            @Override
            public void onFailure(Call<InfoItem> call, Throwable t) {
                Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });

        return rootView;
    }

}



