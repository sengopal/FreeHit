package com.debut.ellipsis.freehit.More.Player;


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

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.Glide.GlideApp;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Info_Fragment extends Fragment {

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

        final View rootView = inflater.inflate(R.layout.fragment_more_player_info, container, false);

        final TextView name = (TextView) rootView.findViewById(R.id.Player_name_stats_info);
        final TextView country = (TextView) rootView.findViewById(R.id.player_country);
        final TextView DOB = (TextView) rootView.findViewById(R.id.DOB);
        final TextView age = (TextView) rootView.findViewById(R.id.Age);
        final TextView BattingStyle = (TextView) rootView.findViewById(R.id.battingStyle);
        final TextView BowlingStyle = (TextView) rootView.findViewById((R.id.BowlingStyle));
        final TextView TeamsPlayed = (TextView) rootView.findViewById((R.id.Teams_Played));

        View viewProgress = (View) rootView.findViewById(R.id.progress);
        mProgressBar = (ProgressBar) viewProgress.findViewById(R.id.progress_bar);

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

                /*CustomImageSizeModel PlayerImage = new CustomImageSizeModelFutureStudio(ImageURL);*/

                RequestBuilder requestBuilder = GlideApp.with(getContext()).load(ImageURL).placeholder(R.drawable.matches).format(DecodeFormat.PREFER_RGB_565);

                requestBuilder.into(articleImage);

                /*GlideApp.with(getContext()).load(PlayerImage).apply(new RequestOptions().placeholder(R.drawable.matches).centerCrop()).into(articleImage);*/
                TextView odiBat = (TextView) rootView.findViewById(R.id.odibattingRanking);
                TextView testBat = (TextView) rootView.findViewById(R.id.testBattingRanking);
                TextView t20Bat = (TextView) rootView.findViewById(R.id.T20BattingRanking);

                String ODI_Batting_rank = "-";
                String TEST_Batting_rank = "-";
                String T20_Batting_rank = "-";


                List<String> batting_rank = info.getBatrank();
                for(int i=0; i < batting_rank.size(); i++)
                {
                    String batting_format_rank = batting_rank.get(i);

                    if(TEST_Batting_rank.equals("-")) {
                        if (batting_format_rank.contains("Test -")) {
                            String[] parts = batting_format_rank.split("Test - ");
                            TEST_Batting_rank = "1";
                            testBat.setText(parts[1]);
                        } else {
                            testBat.setText("--");
                        }
                    }

                    if(ODI_Batting_rank.equals("-")) {
                        if (batting_format_rank.contains("ODI - ")) {
                            String[] parts = batting_format_rank.split("ODI - ");
                            ODI_Batting_rank = "1";
                            odiBat.setText(parts[1]);
                        } else {
                            odiBat.setText("--");
                        }
                    }

                    if(T20_Batting_rank.equals("-")) {
                        if (batting_format_rank.contains("T20I - ")) {
                            String[] parts = batting_format_rank.split("T20I - ");
                            T20_Batting_rank = "1";
                            t20Bat.setText(parts[1]);
                        } else {
                            t20Bat.setText("--");
                        }
                    }
                }


                TextView odibowl = (TextView) rootView.findViewById(R.id.odiBowlingRanking);
                TextView testbowl = (TextView) rootView.findViewById(R.id.testBowlingRanking);
                TextView t20bowl = (TextView) rootView.findViewById(R.id.T20BowlingRanking);


                String ODI_Bowling_rank = "-";
                String TEST_Bowling_rank = "-";
                String T20_Bowling_rank = "-";

                List<String> bowling_rank = info.getBowlrank();
                for(int i=0; i < bowling_rank.size(); i++)
                {
                    String bowling_format_rank = bowling_rank.get(i);

                    if(TEST_Bowling_rank.equals("-")) {
                        if (bowling_format_rank.contains("Test -")) {
                            String[] parts = bowling_format_rank.split("Test - ");
                            TEST_Bowling_rank = "1";
                            testbowl.setText(parts[1]);
                        } else {
                            testbowl.setText("--");
                        }
                    }

                    if(ODI_Bowling_rank.equals("-")) {
                        if (bowling_format_rank.contains("ODI - ")) {
                            String[] parts = bowling_format_rank.split("ODI - ");
                            ODI_Bowling_rank = "1";
                            odibowl.setText(parts[1]);
                        } else {
                            odibowl.setText("--");
                        }
                    }

                    if(T20_Bowling_rank.equals("-")) {
                        if (bowling_format_rank.contains("T20I - ")) {
                            String[] parts = bowling_format_rank.split("T20I - ");
                            T20_Bowling_rank = "1";
                            t20bowl.setText(parts[1]);
                        } else {
                            t20bowl.setText("--");
                        }
                    }
                }


                TextView mom_test = (TextView) rootView.findViewById(R.id.MOTM_Test);
                TextView mom_odi = (TextView) rootView.findViewById(R.id.MOTM_Odi);
                TextView mom_t20 = (TextView) rootView.findViewById(R.id.MOTM_T20);
                TextView mom_wc = (TextView) rootView.findViewById(R.id.MOTM_WorldCup);
                TextView mom_ipl = (TextView) rootView.findViewById(R.id.MOTM_IPL);
                TextView mom_cl = (TextView) rootView.findViewById(R.id.MOTM_CL);

                String ODI_MOM = "-";
                String TEST_MOM = "-";
                String T20_MOM = "-";
                String WorldCup_MOM = "-";
                String IPL_MOM = "-";
                String CL_MOM = "-";

                List<String> MOTM = info.getManofthematch();
                for(int i=0; i < MOTM.size(); i++)
                {
                    String Motm_format = MOTM.get(i);
                    if(TEST_MOM.equals("-")) {
                        if (Motm_format.contains("Test -")) {
                            String[] parts = Motm_format.split("Test - ");
                            TEST_MOM = "1";
                            mom_test.setText(parts[1]);
                        } else {
                            mom_test.setText("--");
                        }
                    }

                    if(ODI_MOM.equals("-")) {
                        if (Motm_format.contains("ODI - ")) {
                            String[] parts = Motm_format.split("ODI - ");
                            ODI_MOM = "1";
                            mom_odi.setText(parts[1]);
                        } else {
                            mom_odi.setText("--");
                        }
                    }

                    if(T20_MOM.equals("-")) {
                        if (Motm_format.contains("T20I - ")) {
                            String[] parts = Motm_format.split("T20I - ");
                            T20_MOM = "1";
                            mom_t20.setText(parts[1]);
                        } else {
                            mom_t20.setText("--");
                        }
                    }

                    if(WorldCup_MOM.equals("-")) {
                        if (Motm_format.contains(" World Cup - ")) {
                            String[] parts = Motm_format.split(" World Cup - ");
                            WorldCup_MOM = "1";
                            mom_wc.setText(parts[1]);
                        } else {
                            mom_wc.setText("--");
                        }
                    }

                    if(IPL_MOM.equals("-")) {
                        if (Motm_format.contains(" IPL - ")) {
                            String[] parts = Motm_format.split(" IPL - ");
                            IPL_MOM = "1";
                            mom_ipl.setText(parts[1]);
                        } else {
                            mom_ipl.setText("--");
                        }
                    }

                    if(CL_MOM.equals("-")) {
                        if (Motm_format.contains(" CL - ")) {
                            String[] parts = Motm_format.split(" CL - ");
                            CL_MOM = "1";
                            mom_cl.setText(parts[1]);
                        } else {
                            mom_cl.setText("--");
                        }
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



