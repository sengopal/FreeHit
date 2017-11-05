package com.debut.ellipsis.freehit.More.Player;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.DecodeFormat;
import com.debut.ellipsis.freehit.Glide.GlideApp;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;

import java.util.List;

public class PlayerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String fragment_name = getArguments().getString("fragment_name");

        View rootView = null;

        if (fragment_name.equals("PLAYER INFO")) {

            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                rootView = inflater.inflate(R.layout.fragment_more_player_info_dark, container, false);
            }

            else  if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO)
            {
                rootView = inflater.inflate(R.layout.fragment_more_player_info, container, false);
            }

            TextView name = rootView.findViewById(R.id.Player_name_stats_info);
            TextView country = rootView.findViewById(R.id.player_country);
            TextView DOB = rootView.findViewById(R.id.DOB);
            TextView age = rootView.findViewById(R.id.Age);
            TextView BattingStyle = rootView.findViewById(R.id.battingStyle);
            TextView BowlingStyle = rootView.findViewById((R.id.BowlingStyle));
            TextView TeamsPlayed = rootView.findViewById((R.id.Teams_Played));
            TextView description = rootView.findViewById((R.id.PlayerInfo));

            PlayerItem info = ((PlayerActivity) getActivity()).getQuery();

            name.setText(info.getName());

            country.setText(info.getNationality());

            DOB.setText(info.getDob());

            age.setText(info.getAge());

            BattingStyle.setText(info.getBatstyle());

            BowlingStyle.setText(info.getBowlstyle());

            TeamsPlayed.setText(info.getTeamsplayed());

            ImageView articleImage = rootView.findViewById(R.id.Player_image);

            String ImageURL = info.getImg();

            if (isAdded()) {

                if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
                {
                    MainActivity.requestBuilder = GlideApp.with(getContext()).load(ImageURL).placeholder(R.drawable.placeholder_dark).format(DecodeFormat.PREFER_RGB_565);
                }
                else
                {
                    MainActivity.requestBuilder = GlideApp.with(getContext()).load(ImageURL).placeholder(R.drawable.placeholder_light).format(DecodeFormat.PREFER_RGB_565);
                }


                MainActivity.requestBuilder.into(articleImage);
            }

            TextView odiBat = rootView.findViewById(R.id.odibattingRanking);
            TextView testBat = rootView.findViewById(R.id.testBattingRanking);
            TextView t20Bat = rootView.findViewById(R.id.T20BattingRanking);

            String ODI_Batting_rank = "-";
            String TEST_Batting_rank = "-";
            String T20_Batting_rank = "-";


            List<String> batting_rank = info.getBatrank();
            for (int i = 0; i < batting_rank.size(); i++) {
                String batting_format_rank = batting_rank.get(i);

                if (TEST_Batting_rank.equals("-")) {
                    if (batting_format_rank.contains("Test -")) {
                        String[] parts = batting_format_rank.split("Test - ");
                        TEST_Batting_rank = "1";
                        testBat.setText(parts[1]);
                    } else {
                        testBat.setText("--");
                    }
                }

                if (ODI_Batting_rank.equals("-")) {
                    if (batting_format_rank.contains("ODI - ")) {
                        String[] parts = batting_format_rank.split("ODI - ");
                        ODI_Batting_rank = "1";
                        odiBat.setText(parts[1]);
                    } else {
                        odiBat.setText("--");
                    }
                }

                if (T20_Batting_rank.equals("-")) {
                    if (batting_format_rank.contains("T20I - ")) {
                        String[] parts = batting_format_rank.split("T20I - ");
                        T20_Batting_rank = "1";
                        t20Bat.setText(parts[1]);
                    } else {
                        t20Bat.setText("--");
                    }
                }
            }


            TextView odibowl = rootView.findViewById(R.id.odiBowlingRanking);
            TextView testbowl = rootView.findViewById(R.id.testBowlingRanking);
            TextView t20bowl = rootView.findViewById(R.id.T20BowlingRanking);


            String ODI_Bowling_rank = "-";
            String TEST_Bowling_rank = "-";
            String T20_Bowling_rank = "-";

            List<String> bowling_rank = info.getBowlrank();
            for (int i = 0; i < bowling_rank.size(); i++) {
                String bowling_format_rank = bowling_rank.get(i);

                if (TEST_Bowling_rank.equals("-")) {
                    if (bowling_format_rank.contains("Test -")) {
                        String[] parts = bowling_format_rank.split("Test - ");
                        TEST_Bowling_rank = "1";
                        testbowl.setText(parts[1]);
                    } else {
                        testbowl.setText("--");
                    }
                }

                if (ODI_Bowling_rank.equals("-")) {
                    if (bowling_format_rank.contains("ODI - ")) {
                        String[] parts = bowling_format_rank.split("ODI - ");
                        ODI_Bowling_rank = "1";
                        odibowl.setText(parts[1]);
                    } else {
                        odibowl.setText("--");
                    }
                }

                if (T20_Bowling_rank.equals("-")) {
                    if (bowling_format_rank.contains("T20I - ")) {
                        String[] parts = bowling_format_rank.split("T20I - ");
                        T20_Bowling_rank = "1";
                        t20bowl.setText(parts[1]);
                    } else {
                        t20bowl.setText("--");
                    }
                }
            }


            TextView mom_test = rootView.findViewById(R.id.MOTM_Test);
            TextView mom_odi = rootView.findViewById(R.id.MOTM_Odi);
            TextView mom_t20 = rootView.findViewById(R.id.MOTM_T20);
            TextView mom_wc = rootView.findViewById(R.id.MOTM_WorldCup);
            TextView mom_ipl = rootView.findViewById(R.id.MOTM_IPL);
            TextView mom_cl = rootView.findViewById(R.id.MOTM_CL);

            String ODI_MOM = "-";
            String TEST_MOM = "-";
            String T20_MOM = "-";
            String WorldCup_MOM = "-";
            String IPL_MOM = "-";
            String CL_MOM = "-";

            List<String> MOTM = info.getManofthematch();
            for (int i = 0; i < MOTM.size(); i++) {
                String Motm_format = MOTM.get(i);
                if (TEST_MOM.equals("-")) {
                    if (Motm_format.contains("Test -")) {
                        String[] parts = Motm_format.split("Test - ");
                        TEST_MOM = "1";
                        mom_test.setText(parts[1]);
                    } else {
                        mom_test.setText("--");
                    }
                }

                if (ODI_MOM.equals("-")) {
                    if (Motm_format.contains("ODI - ")) {
                        String[] parts = Motm_format.split("ODI - ");
                        ODI_MOM = "1";
                        mom_odi.setText(parts[1]);
                    } else {
                        mom_odi.setText("--");
                    }
                }

                if (T20_MOM.equals("-")) {
                    if (Motm_format.contains("T20I - ")) {
                        String[] parts = Motm_format.split("T20I - ");
                        T20_MOM = "1";
                        mom_t20.setText(parts[1]);
                    } else {
                        mom_t20.setText("--");
                    }
                }

                if (WorldCup_MOM.equals("-")) {
                    if (Motm_format.contains(" World Cup - ")) {
                        String[] parts = Motm_format.split(" World Cup - ");
                        WorldCup_MOM = "1";
                        mom_wc.setText(parts[1]);
                    } else {
                        mom_wc.setText("--");
                    }
                }

                if (IPL_MOM.equals("-")) {
                    if (Motm_format.contains(" IPL - ")) {
                        String[] parts = Motm_format.split(" IPL - ");
                        IPL_MOM = "1";
                        mom_ipl.setText(parts[1]);
                    } else {
                        mom_ipl.setText("--");
                    }
                }

                if (CL_MOM.equals("-")) {
                    if (Motm_format.contains(" CL - ")) {
                        String[] parts = Motm_format.split(" CL - ");
                        CL_MOM = "1";
                        mom_cl.setText(parts[1]);
                    } else {
                        mom_cl.setText("--");
                    }
                }
            }

            description.setText(info.getDesc());



        } else {

            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {

                rootView = inflater.inflate(R.layout.fragment_more_player_batting_bowling_dark, container, false);
            }

            else  if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO)
            {
                rootView = inflater.inflate(R.layout.fragment_more_player_batting_bowling, container, false);
            }

            TextView Matches_Innings_label = rootView.findViewById(R.id.Matches_Innings_label);
            TextView m_i_test = rootView.findViewById(R.id.m_i_test);
            TextView m_i_odi = rootView.findViewById(R.id.m_i_odi);
            TextView m_i_t20 = rootView.findViewById(R.id.m_i_t20);
            TextView m_i_ipl = rootView.findViewById(R.id.m_i_ipl);

            TextView Innings_Overs_label = rootView.findViewById(R.id.Innings_Overs_label);
            TextView i_o_test = rootView.findViewById(R.id.i_o_test);
            TextView i_o_odi = rootView.findViewById(R.id.i_o_odi);
            TextView i_o_t20 = rootView.findViewById(R.id.i_o_t20);
            TextView i_o_ipl = rootView.findViewById(R.id.i_o_ipl);

            TextView NotOut_Maidens_Label = rootView.findViewById(R.id.NotOut_Maidens_Label);
            TextView no_m_test = rootView.findViewById(R.id.no_m_test);
            TextView no_m_odi = rootView.findViewById(R.id.no_m_odi);
            TextView no_m_t20 = rootView.findViewById(R.id.no_m_t20);
            TextView no_m_ipl = rootView.findViewById(R.id.no_m_ipl);


            TextView r_r_test = rootView.findViewById(R.id.r_r_test);
            TextView r_r_odi = rootView.findViewById(R.id.r_r_odi);
            TextView r_r_t20 = rootView.findViewById(R.id.r_r_t20);
            TextView r_r_ipl = rootView.findViewById(R.id.r_r_ipl);


            TextView highest_Wickets_label = rootView.findViewById(R.id.highest_Wickets_label);
            TextView h_w_test = rootView.findViewById(R.id.h_w_test);
            TextView h_w_odi = rootView.findViewById(R.id.h_w_odi);
            TextView h_w_t20 = rootView.findViewById(R.id.h_w_t20);
            TextView h_w_ipl = rootView.findViewById(R.id.h_w_ipl);

            TextView hundreds_best_label = rootView.findViewById(R.id.hundreds_best_label);
            TextView h_b_test = rootView.findViewById(R.id.h_b_test);
            TextView h_b_odi = rootView.findViewById(R.id.h_b_odi);
            TextView h_b_t20 = rootView.findViewById(R.id.h_b_t20);
            TextView h_b_ipl = rootView.findViewById(R.id.h_b_ipl);

            TextView fifties_3w_label = rootView.findViewById(R.id.fifties_3w_label);
            TextView f_3w_test = rootView.findViewById(R.id.f_3w_test);
            TextView f_3w_odi = rootView.findViewById(R.id.f_3w_odi);
            TextView f_3w_t20 = rootView.findViewById(R.id.f_3w_t20);
            TextView f_3w_ipl = rootView.findViewById(R.id.f_3w_ipl);

            TextView fours_5wickets_label = rootView.findViewById(R.id.fours_5wickets_label);
            TextView f_5w_test = rootView.findViewById(R.id.f_5w_test);
            TextView f_5w_odi = rootView.findViewById(R.id.f_5w_odi);
            TextView f_5w_t20 = rootView.findViewById(R.id.f_5w_t20);
            TextView f_5w_ipl = rootView.findViewById(R.id.f_5w_ipl);

            TextView sixes_average_label = rootView.findViewById(R.id.sixes_average_label);
            TextView s_avg_test = rootView.findViewById(R.id.s_avg_test);
            TextView s_avg_odi = rootView.findViewById(R.id.s_avg_odi);
            TextView s_avg_t20 = rootView.findViewById(R.id.s_avg_t20);
            TextView s_avg_ipl = rootView.findViewById(R.id.s_avg_ipl);

            TextView average_economy_label = rootView.findViewById(R.id.average_economy_label);
            TextView avg_eco_test = rootView.findViewById(R.id.avg_eco_test);
            TextView avg_eco_odi = rootView.findViewById(R.id.avg_eco_odi);
            TextView avg_eco_t20 = rootView.findViewById(R.id.avg_eco_t20);
            TextView avg_eco_ipl = rootView.findViewById(R.id.avg_eco_ipl);

            TextView sr_test = rootView.findViewById(R.id.sr_test);
            TextView sr_odi = rootView.findViewById(R.id.sr_odi);
            TextView sr_t20 = rootView.findViewById(R.id.sr_t20);
            TextView sr_ipl = rootView.findViewById(R.id.sr_ipl);


            PlayerItem info = ((PlayerActivity) getActivity()).getQuery();

            if (fragment_name.equals("BATTING")) {
                //Number Of Matches in Test,Odi,T20,IPL
                m_i_test.setText(info.getBatstats().getTest().getMatches());
                m_i_odi.setText(info.getBatstats().getOdi().getMatches());
                m_i_t20.setText(info.getBatstats().getT20().getMatches());
                m_i_ipl.setText(info.getBatstats().getIpl().getMatches());

                //Number Of Innings Played in Test,Odi,T20,IPL
                i_o_test.setText(info.getBatstats().getTest().getInnbat());
                i_o_odi.setText(info.getBatstats().getOdi().getInnbat());
                i_o_t20.setText(info.getBatstats().getT20().getInnbat());
                i_o_ipl.setText(info.getBatstats().getIpl().getInnbat());

                //Number of Not Outs in Test,Odi,T20,IPL
                no_m_test.setText(info.getBatstats().getTest().getNotout());
                no_m_odi.setText(info.getBatstats().getOdi().getNotout());
                no_m_t20.setText(info.getBatstats().getT20().getNotout());
                no_m_ipl.setText(info.getBatstats().getIpl().getNotout());

                //Number Of Runs in Test,Odi,T20,IPL
                r_r_test.setText(info.getBatstats().getTest().getRuns());
                r_r_odi.setText(info.getBatstats().getOdi().getRuns());
                r_r_t20.setText(info.getBatstats().getT20().getRuns());
                r_r_ipl.setText(info.getBatstats().getIpl().getRuns());

                //Highest Score in Test,Odi,T20,IPL
                h_w_test.setText(info.getBatstats().getTest().getHighestinn());
                h_w_odi.setText(info.getBatstats().getOdi().getHighestinn());
                h_w_t20.setText(info.getBatstats().getT20().getHighestinn());
                h_w_ipl.setText(info.getBatstats().getIpl().getHighestinn());

                //Centuries in Test,Odi,T20,IPL
                h_b_test.setText(info.getBatstats().getTest().getHundreds());
                h_b_odi.setText(info.getBatstats().getOdi().getHundreds());
                h_b_t20.setText(info.getBatstats().getT20().getHundreds());
                h_b_ipl.setText(info.getBatstats().getIpl().getHundreds());

                //Fifties in Test,Odi,T20,IPL
                f_3w_test.setText(info.getBatstats().getTest().getFifties());
                f_3w_odi.setText(info.getBatstats().getOdi().getFifties());
                f_3w_t20.setText(info.getBatstats().getT20().getFifties());
                f_3w_ipl.setText(info.getBatstats().getIpl().getFifties());

                //Fours in Test,Odi,T20,IPL
                f_5w_test.setText(info.getBatstats().getTest().getFours());
                f_5w_odi.setText(info.getBatstats().getOdi().getFours());
                f_5w_t20.setText(info.getBatstats().getT20().getFours());
                f_5w_ipl.setText(info.getBatstats().getIpl().getFours());

                //Sixes in Test,Odi,T20,IPL
                s_avg_test.setText(info.getBatstats().getTest().getSixes());
                s_avg_odi.setText(info.getBatstats().getOdi().getSixes());
                s_avg_t20.setText(info.getBatstats().getT20().getSixes());
                s_avg_ipl.setText(info.getBatstats().getIpl().getSixes());

                //batting Average in Test,Odi,T20,IPL
                avg_eco_test.setText(info.getBatstats().getTest().getBatavg());
                avg_eco_odi.setText(info.getBatstats().getOdi().getBatavg());
                avg_eco_t20.setText(info.getBatstats().getT20().getBatavg());
                avg_eco_ipl.setText(info.getBatstats().getIpl().getBatavg());

                //Batting Strike Rate in Test,Odi,T20,IPL
                sr_test.setText(info.getBatstats().getTest().getBatstr());
                sr_odi.setText(info.getBatstats().getOdi().getBatstr());
                sr_t20.setText(info.getBatstats().getT20().getBatstr());
                sr_ipl.setText(info.getBatstats().getIpl().getBatstr());
            }

            if (fragment_name.equals("BOWLING")) {
                //Number Of Innings in Test,Odi,T20,IPL
                Matches_Innings_label.setText(R.string.innings);
                m_i_test.setText(info.getBowlstats().getTest().getinnbowled());
                m_i_odi.setText(info.getBowlstats().getOdi().getinnbowled());
                m_i_t20.setText(info.getBowlstats().getT20().getinnbowled());
                m_i_ipl.setText(info.getBowlstats().getIpl().getinnbowled());

                //Number Of Overs Bowled in Test,Odi,T20,IPL
                Innings_Overs_label.setText(R.string.overs);
                i_o_test.setText(info.getBowlstats().getTest().getoversbowled());
                i_o_odi.setText(info.getBowlstats().getOdi().getoversbowled());
                i_o_t20.setText(info.getBowlstats().getT20().getoversbowled());
                i_o_ipl.setText(info.getBowlstats().getIpl().getoversbowled());

                //Number Of Maidens in Test,Odi,T20,IPL
                NotOut_Maidens_Label.setText(R.string.maidens);
                no_m_test.setText(info.getBowlstats().getTest().getmaidens());
                no_m_odi.setText(info.getBowlstats().getOdi().getmaidens());
                no_m_t20.setText(info.getBowlstats().getT20().getmaidens());
                no_m_ipl.setText(info.getBowlstats().getIpl().getmaidens());

                //Number of Runs Conceded in Test,Odi,T20,IPL
                r_r_test.setText(info.getBowlstats().getTest().getrunsgiven());
                r_r_odi.setText(info.getBowlstats().getOdi().getrunsgiven());
                r_r_t20.setText(info.getBowlstats().getT20().getrunsgiven());
                r_r_ipl.setText(info.getBowlstats().getIpl().getrunsgiven());

                //Number Of Wickets taken in Test,Odi,T20,IPL
                highest_Wickets_label.setText(R.string.wickets);
                h_w_test.setText(info.getBowlstats().getTest().getwicktaken());
                h_w_odi.setText(info.getBowlstats().getOdi().getwicktaken());
                h_w_t20.setText(info.getBowlstats().getT20().getwicktaken());
                h_w_ipl.setText(info.getBowlstats().getIpl().getwicktaken());

                //Bes innings in Test,Odi,T20,IPL
                hundreds_best_label.setText(R.string.best);
                h_b_test.setText(info.getBowlstats().getTest().getbestinn());
                h_b_odi.setText(info.getBowlstats().getOdi().getbestinn());
                h_b_t20.setText(info.getBowlstats().getT20().getbestinn());
                h_b_ipl.setText(info.getBowlstats().getIpl().getbestinn());

                //Number Of 3 Wicket hauls in Test,Odi,T20,IPL
                fifties_3w_label.setText(R.string.wickets_3);
                f_3w_test.setText(info.getBowlstats().getTest().getthreewick());
                f_3w_odi.setText(info.getBowlstats().getOdi().getthreewick());
                f_3w_t20.setText(info.getBowlstats().getT20().getthreewick());
                f_3w_ipl.setText(info.getBowlstats().getIpl().getthreewick());

                //Number of 5 Wicket hauls in Test,Odi,T20,IPL
                fours_5wickets_label.setText(R.string.wickets_5);
                f_5w_test.setText(info.getBowlstats().getTest().getfivewick());
                f_5w_odi.setText(info.getBowlstats().getOdi().getfivewick());
                f_5w_t20.setText(info.getBowlstats().getT20().getfivewick());
                f_5w_ipl.setText(info.getBowlstats().getIpl().getfivewick());

                //Bowling Average in Test,Odi,T20,IPL
                sixes_average_label.setText(R.string.average);
                s_avg_test.setText(info.getBowlstats().getTest().getbowlingavg());
                s_avg_odi.setText(info.getBowlstats().getOdi().getbowlingavg());
                s_avg_t20.setText(info.getBowlstats().getT20().getbowlingavg());
                s_avg_ipl.setText(info.getBowlstats().getIpl().getbowlingavg());

                //Economy in Test,Odi,T20,IPL
                average_economy_label.setText(R.string.economy);
                avg_eco_test.setText(info.getBowlstats().getTest().geteconomy());
                avg_eco_odi.setText(info.getBowlstats().getOdi().geteconomy());
                avg_eco_t20.setText(info.getBowlstats().getT20().geteconomy());
                avg_eco_ipl.setText(info.getBowlstats().getIpl().geteconomy());

                //Strike Rate in Test,Odi,T20,IPL
                sr_test.setText(info.getBowlstats().getTest().getstrrate());
                sr_odi.setText(info.getBowlstats().getOdi().getstrrate());
                sr_t20.setText(info.getBowlstats().getT20().getstrrate());
                sr_ipl.setText(info.getBowlstats().getIpl().getstrrate());
            }
        }


        return rootView;
    }

}
