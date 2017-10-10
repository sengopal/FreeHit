package com.debut.ellipsis.freehit.More.Player;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Bowling_Fragment extends Fragment {

    private String player_url;
    private ProgressBar mProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_more_player_batting_bowling_gridview, container, false);
        Intent i = getActivity().getIntent();
        player_url = i.getStringExtra("player_url");


        final String[] gridViewString = new String[60];
        final GridView androidGridView;
        final Batting_Bowling_item_adapter adapterViewAndroid = new Batting_Bowling_item_adapter(getContext(), gridViewString);
        androidGridView = (GridView) rootView.findViewById(R.id.grid_view_batting_bowling);

        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);


        androidGridView.setAdapter(adapterViewAndroid);
        setHeightDynamically(androidGridView);
        gridViewString[0] = "BOWLING";
        gridViewString[1] = "TEST";
        gridViewString[2] = "ODI";
        gridViewString[3] = "T20";
        gridViewString[4] = "IPL";
        gridViewString[5] = "INNINGS";
        gridViewString[10] = "OVERS";
        gridViewString[15] = "MAIDENS";
        gridViewString[20] = "RUNS ";
        gridViewString[25] = "WICKETS";
        gridViewString[30] = "BEST";
        gridViewString[35] = "3w";
        gridViewString[40] = "5w";
        gridViewString[45] = "AVERAGE";
        gridViewString[50] = "ECONOMY";
        gridViewString[55] = "STRIKE\nRATE";

        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        Call<BowlingItem> call = apiInterface.doGetBowlingInfo(player_url);
        call.enqueue(new Callback<BowlingItem>() {
            @Override
            public void onResponse(Call<BowlingItem> call, Response<BowlingItem> response) {
                BowlingItem info = response.body();
                //Number Of Innings in Test,Odi,T20,IPL
                gridViewString[6] = info.getBowlstats().getTest().getinnbowled();
                gridViewString[7] = info.getBowlstats().getOdi().getinnbowled();
                gridViewString[8] = info.getBowlstats().getT20().getinnbowled();
                gridViewString[9] = info.getBowlstats().getIpl().getinnbowled();

                //Number Of Overs Bowled in Test,Odi,T20,IPL
                gridViewString[11] = info.getBowlstats().getTest().getoversbowled();
                gridViewString[12] = info.getBowlstats().getOdi().getoversbowled();
                gridViewString[13] = info.getBowlstats().getT20().getoversbowled();
                gridViewString[14] = info.getBowlstats().getIpl().getoversbowled();

                //Number Of Maidens in Test,Odi,T20,IPL
                gridViewString[16] = info.getBowlstats().getTest().getmaidens();
                gridViewString[17] = info.getBowlstats().getOdi().getmaidens();
                gridViewString[18] = info.getBowlstats().getT20().getmaidens();
                gridViewString[19] = info.getBowlstats().getIpl().getmaidens();

                //Number of Runs Conceeded in Test,Odi,T20,IPL
                gridViewString[21] = info.getBowlstats().getTest().getrunsgiven();
                gridViewString[22] = info.getBowlstats().getOdi().getrunsgiven();
                gridViewString[23] = info.getBowlstats().getT20().getrunsgiven();
                gridViewString[24] = info.getBowlstats().getIpl().getrunsgiven();

                //Number Of Wickets taken in Test,Odi,T20,IPL
                gridViewString[26] = info.getBowlstats().getTest().getwicktaken();
                gridViewString[27] = info.getBowlstats().getOdi().getwicktaken();
                gridViewString[28] = info.getBowlstats().getT20().getwicktaken();
                gridViewString[29] = info.getBowlstats().getIpl().getwicktaken();

                //Bes innings in Test,Odi,T20,IPL
                gridViewString[31] = info.getBowlstats().getTest().getbestinn();
                gridViewString[32] = info.getBowlstats().getOdi().getbestinn();
                gridViewString[33] = info.getBowlstats().getT20().getbestinn();
                gridViewString[34] = info.getBowlstats().getIpl().getbestinn();

                //Number Of 3 Wicket hauls in Test,Odi,T20,IPL
                gridViewString[36] = info.getBowlstats().getTest().getthreewick();
                gridViewString[37] = info.getBowlstats().getOdi().getthreewick();
                gridViewString[38] = info.getBowlstats().getT20().getthreewick();
                gridViewString[39] = info.getBowlstats().getIpl().getthreewick();

                //Number of 5 Wicket hauls in Test,Odi,T20,IPL
                gridViewString[41] = info.getBowlstats().getTest().getfivewick();
                gridViewString[42] = info.getBowlstats().getOdi().getfivewick();
                gridViewString[43] = info.getBowlstats().getT20().getfivewick();
                gridViewString[44] = info.getBowlstats().getIpl().getfivewick();

                //Bowling Average in Test,Odi,T20,IPL
                gridViewString[46] = info.getBowlstats().getTest().getbowlingavg();
                gridViewString[47] = info.getBowlstats().getOdi().getbowlingavg();
                gridViewString[48] = info.getBowlstats().getT20().getbowlingavg();
                gridViewString[49] = info.getBowlstats().getIpl().getbowlingavg();

                //Economy in Test,Odi,T20,IPL
                gridViewString[51] = info.getBowlstats().getTest().geteconomy();
                gridViewString[52] = info.getBowlstats().getOdi().geteconomy();
                gridViewString[53] = info.getBowlstats().getT20().geteconomy();
                gridViewString[54] = info.getBowlstats().getIpl().geteconomy();

                //Strike Rate in Test,Odi,T20,IPL
                gridViewString[56] = info.getBowlstats().getTest().getstrrate();
                gridViewString[57] = info.getBowlstats().getOdi().getstrrate();
                gridViewString[58] = info.getBowlstats().getT20().getstrrate();
                gridViewString[59] = info.getBowlstats().getIpl().getstrrate();


                adapterViewAndroid.notifyDataSetChanged();
                androidGridView.invalidateViews();

                mProgressBar.setVisibility(View.INVISIBLE);
                androidGridView.setAdapter(adapterViewAndroid);


            }

            @Override
            public void onFailure(Call<BowlingItem> call, Throwable t) {

            }
        });


        return rootView;
    }

    public static void setHeightDynamically(GridView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i = i + 5) {
            view = listAdapter.getView(i, view, listView);
            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
