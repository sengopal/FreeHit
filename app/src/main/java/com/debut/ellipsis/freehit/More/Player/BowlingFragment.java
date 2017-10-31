package com.debut.ellipsis.freehit.More.Player;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.debut.ellipsis.freehit.R;

public class BowlingFragment  extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_more_player_batting_bowling, container, false);

        final TextView Matches_Innings_label = rootView.findViewById(R.id.Matches_Innings_label);
        final TextView m_i_test = rootView.findViewById(R.id.m_i_test);
        final TextView m_i_odi = rootView.findViewById(R.id.m_i_odi);
        final TextView m_i_t20 = rootView.findViewById(R.id.m_i_t20);
        final TextView m_i_ipl = rootView.findViewById(R.id.m_i_ipl);

        final TextView Innings_Overs_label = rootView.findViewById(R.id.Innings_Overs_label);
        final TextView i_o_test = rootView.findViewById(R.id.i_o_test);
        final TextView i_o_odi = rootView.findViewById(R.id.i_o_odi);
        final TextView i_o_t20 = rootView.findViewById(R.id.i_o_t20);
        final TextView i_o_ipl = rootView.findViewById(R.id.i_o_ipl);

        final TextView NotOut_Maidens_Label = rootView.findViewById(R.id.NotOut_Maidens_Label);
        final TextView no_m_test = rootView.findViewById(R.id.no_m_test);
        final TextView no_m_odi = rootView.findViewById(R.id.no_m_odi);
        final TextView no_m_t20 = rootView.findViewById(R.id.no_m_t20);
        final TextView no_m_ipl = rootView.findViewById(R.id.no_m_ipl);


        final TextView r_r_test = rootView.findViewById(R.id.r_r_test);
        final TextView r_r_odi = rootView.findViewById(R.id.r_r_odi);
        final TextView r_r_t20 = rootView.findViewById(R.id.r_r_t20);
        final TextView r_r_ipl = rootView.findViewById(R.id.r_r_ipl);


        final TextView highest_Wickets_label = rootView.findViewById(R.id.highest_Wickets_label);
        final TextView h_w_test = rootView.findViewById(R.id.h_w_test);
        final TextView h_w_odi = rootView.findViewById(R.id.h_w_odi);
        final TextView h_w_t20 = rootView.findViewById(R.id.h_w_t20);
        final TextView h_w_ipl = rootView.findViewById(R.id.h_w_ipl);

        final TextView hundreds_best_label = rootView.findViewById(R.id.hundreds_best_label);
        final TextView h_b_test = rootView.findViewById(R.id.h_b_test);
        final TextView h_b_odi = rootView.findViewById(R.id.h_b_odi);
        final TextView h_b_t20 = rootView.findViewById(R.id.h_b_t20);
        final TextView h_b_ipl = rootView.findViewById(R.id.h_b_ipl);

        final TextView fifties_3w_label = rootView.findViewById(R.id.fifties_3w_label);
        final TextView f_3w_test = rootView.findViewById(R.id.f_3w_test);
        final TextView f_3w_odi = rootView.findViewById(R.id.f_3w_odi);
        final TextView f_3w_t20 = rootView.findViewById(R.id.f_3w_t20);
        final TextView f_3w_ipl = rootView.findViewById(R.id.f_3w_ipl);

        final TextView fours_5wickets_label = rootView.findViewById(R.id.fours_5wickets_label);
        final TextView f_5w_test = rootView.findViewById(R.id.f_5w_test);
        final TextView f_5w_odi = rootView.findViewById(R.id.f_5w_odi);
        final TextView f_5w_t20 = rootView.findViewById(R.id.f_5w_t20);
        final TextView f_5w_ipl = rootView.findViewById(R.id.f_5w_ipl);

        final TextView sixes_average_label = rootView.findViewById(R.id.sixes_average_label);
        final TextView s_avg_test = rootView.findViewById(R.id.s_avg_test);
        final TextView s_avg_odi = rootView.findViewById(R.id.s_avg_odi);
        final TextView s_avg_t20 = rootView.findViewById(R.id.s_avg_t20);
        final TextView s_avg_ipl = rootView.findViewById(R.id.s_avg_ipl);

        final TextView average_economy_label = rootView.findViewById(R.id.average_economy_label);
        final TextView avg_eco_test = rootView.findViewById(R.id.avg_eco_test);
        final TextView avg_eco_odi = rootView.findViewById(R.id.avg_eco_odi);
        final TextView avg_eco_t20 = rootView.findViewById(R.id.avg_eco_t20);
        final TextView avg_eco_ipl = rootView.findViewById(R.id.avg_eco_ipl);

        final TextView sr_test = rootView.findViewById(R.id.sr_test);
        final TextView sr_odi = rootView.findViewById(R.id.sr_odi);
        final TextView sr_t20 = rootView.findViewById(R.id.sr_t20);
        final TextView sr_ipl = rootView.findViewById(R.id.sr_ipl);


        PlayerItem info = ((PlayerActivity) getActivity()).getQuery();

        //Number Of Innings in Test,Odi,T20,IPL
        Matches_Innings_label.setText("INNINGS");
        m_i_test.setText(info.getBowlstats().getTest().getinnbowled());
        m_i_odi.setText(info.getBowlstats().getOdi().getinnbowled());
        m_i_t20.setText(info.getBowlstats().getT20().getinnbowled());
        m_i_ipl.setText(info.getBowlstats().getIpl().getinnbowled());

        //Number Of Overs Bowled in Test,Odi,T20,IPL
        Innings_Overs_label.setText("OVERS");
        i_o_test.setText(info.getBowlstats().getTest().getoversbowled());
        i_o_odi.setText(info.getBowlstats().getOdi().getoversbowled());
        i_o_t20.setText(info.getBowlstats().getT20().getoversbowled());
        i_o_ipl.setText(info.getBowlstats().getIpl().getoversbowled());

        //Number Of Maidens in Test,Odi,T20,IPL
        NotOut_Maidens_Label.setText("MAIDENS");
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
        highest_Wickets_label.setText("WICKETS");
        h_w_test.setText(info.getBowlstats().getTest().getwicktaken());
        h_w_odi.setText(info.getBowlstats().getOdi().getwicktaken());
        h_w_t20.setText(info.getBowlstats().getT20().getwicktaken());
        h_w_ipl.setText(info.getBowlstats().getIpl().getwicktaken());

        //Bes innings in Test,Odi,T20,IPL
        hundreds_best_label.setText("BEST");
        h_b_test.setText(info.getBowlstats().getTest().getbestinn());
        h_b_odi.setText(info.getBowlstats().getOdi().getbestinn());
        h_b_t20.setText(info.getBowlstats().getT20().getbestinn());
        h_b_ipl.setText(info.getBowlstats().getIpl().getbestinn());

        //Number Of 3 Wicket hauls in Test,Odi,T20,IPL
        fifties_3w_label.setText("3W");
        f_3w_test.setText(info.getBowlstats().getTest().getthreewick());
        f_3w_odi.setText(info.getBowlstats().getOdi().getthreewick());
        f_3w_t20.setText(info.getBowlstats().getT20().getthreewick());
        f_3w_ipl.setText(info.getBowlstats().getIpl().getthreewick());

        //Number of 5 Wicket hauls in Test,Odi,T20,IPL
        fours_5wickets_label.setText("5W");
        f_5w_test.setText(info.getBowlstats().getTest().getfivewick());
        f_5w_odi.setText(info.getBowlstats().getOdi().getfivewick());
        f_5w_t20.setText(info.getBowlstats().getT20().getfivewick());
        f_5w_ipl.setText(info.getBowlstats().getIpl().getfivewick());

        //Bowling Average in Test,Odi,T20,IPL
        sixes_average_label.setText("AVERAGE");
        s_avg_test.setText(info.getBowlstats().getTest().getbowlingavg());
        s_avg_odi.setText(info.getBowlstats().getOdi().getbowlingavg());
        s_avg_t20.setText(info.getBowlstats().getT20().getbowlingavg());
        s_avg_ipl.setText(info.getBowlstats().getIpl().getbowlingavg());

        //Economy in Test,Odi,T20,IPL
        average_economy_label.setText("ECONOMY");
        avg_eco_test.setText(info.getBowlstats().getTest().geteconomy());
        avg_eco_odi.setText(info.getBowlstats().getOdi().geteconomy());
        avg_eco_t20.setText(info.getBowlstats().getT20().geteconomy());
        avg_eco_ipl.setText(info.getBowlstats().getIpl().geteconomy());

        //Strike Rate in Test,Odi,T20,IPL
        sr_test.setText(info.getBowlstats().getTest().getstrrate());
        sr_odi.setText(info.getBowlstats().getOdi().getstrrate());
        sr_t20.setText(info.getBowlstats().getT20().getstrrate());
        sr_ipl.setText(info.getBowlstats().getIpl().getstrrate());


        return rootView;
    }
}
