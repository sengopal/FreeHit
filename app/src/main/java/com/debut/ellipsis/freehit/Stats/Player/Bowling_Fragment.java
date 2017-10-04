package com.debut.ellipsis.freehit.Stats.Player;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Bowling_Fragment extends Fragment {
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_stats_player_batting_bowling_gridview, container, false);
        final String[] gridViewString=new String[60];
        final GridView androidGridView;
        final Bowling_item_adapter adapterViewAndroid = new Bowling_item_adapter(getContext(), gridViewString);
        androidGridView = (GridView) rootView.findViewById(R.id.grid_view_batting_bowling);


        androidGridView.setAdapter(adapterViewAndroid);
        setGridViewHeightBasedOnChildren(androidGridView, 5);
        gridViewString[0]= "Bowlinf";gridViewString[1]="Test";gridViewString[2]="Odi";gridViewString[3]="T20";gridViewString[4]="IPL";
        gridViewString[5]="Innings";
        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        Call<Bowling_Items> call = apiInterface.doGetBowlingInfo();
        call.enqueue(new Callback<Bowling_Items>() {
            @Override
            public void onResponse(Call<Bowling_Items> call, Response<Bowling_Items> response) {
                Bowling_Items info = response.body();
                            /* String tmat*/gridViewString[6]=info.getBowlstats().getTest().getinnbowled();
                            /* String omat*/gridViewString[7]=info.getBowlstats().getOdi().getinnbowled();

                             /*String t20mat*/gridViewString[8]=info.getBowlstats().getT20().getinnbowled();
                            /* String imat*/gridViewString[9]=info.getBowlstats().getIpl().getinnbowled();
                gridViewString[10]="Overs";
                           /*  String tinn*/gridViewString[11]=info.getBowlstats().getTest().getoversbowled();
                            /* String oinn*/gridViewString[12]=info.getBowlstats().getOdi().getoversbowled();
                            /* String t20inn*/gridViewString[13]=info.getBowlstats().getT20().getoversbowled();
                             /*String iinn*/gridViewString[14]=info.getBowlstats().getIpl().getoversbowled();
                gridViewString[15]="Maidens";
                gridViewString[16]=info.getBowlstats().getTest().getmaidens();
                gridViewString[17]=info.getBowlstats().getOdi().getmaidens();
                gridViewString[18]=info.getBowlstats().getT20().getmaidens();
                gridViewString[19]=info.getBowlstats().getIpl().getmaidens();
                gridViewString[20]="Runs ";
                gridViewString[21]=info.getBowlstats().getTest().getrunsgiven();
                gridViewString[22]=info.getBowlstats().getOdi().getrunsgiven();
                gridViewString[23]=info.getBowlstats().getT20().getrunsgiven();
                gridViewString[24]=info.getBowlstats().getIpl().getrunsgiven();
                gridViewString[25]="Wickets";
                gridViewString[26]=info.getBowlstats().getTest().getwicktaken();
                gridViewString[27]=info.getBowlstats().getOdi().getwicktaken();
                gridViewString[28]=info.getBowlstats().getT20().getwicktaken();
                gridViewString[29]=info.getBowlstats().getIpl().getwicktaken();
                gridViewString[30]="Best";
                gridViewString[31]=info.getBowlstats().getTest().getbestinn();
                gridViewString[32]=info.getBowlstats().getOdi().getbestinn();
                gridViewString[33]=info.getBowlstats().getT20().getbestinn();
                gridViewString[34]=info.getBowlstats().getIpl().getbestinn();
                gridViewString[35]="3w";
                gridViewString[36]=info.getBowlstats().getTest().getthreewick();
                gridViewString[37]=info.getBowlstats().getOdi().getthreewick();
                gridViewString[38]=info.getBowlstats().getT20().getthreewick();
                gridViewString[39]=info.getBowlstats().getIpl().getthreewick();
                gridViewString[40]="5w";
                gridViewString[41]=info.getBowlstats().getTest().getfivewick();
                gridViewString[42]=info.getBowlstats().getOdi().getfivewick();
                gridViewString[43]=info.getBowlstats().getT20().getfivewick();
                gridViewString[44]=info.getBowlstats().getIpl().getfivewick();
                gridViewString[45]="Average";
                gridViewString[46]=info.getBowlstats().getTest().getbowlingavg();
                gridViewString[47]=info.getBowlstats().getOdi().getbowlingavg();
                gridViewString[48]=info.getBowlstats().getT20().getbowlingavg();
                gridViewString[49]=info.getBowlstats().getIpl().getbowlingavg();
                gridViewString[50]="Economy";
                gridViewString[51]=info.getBowlstats().getTest().geteconomy();
                gridViewString[52]=info.getBowlstats().getOdi().geteconomy();
                gridViewString[53]=info.getBowlstats().getT20().geteconomy();
                gridViewString[54]=info.getBowlstats().getIpl().geteconomy();
                gridViewString[55]="Strike Rate";
                gridViewString[56]=info.getBowlstats().getTest().getstrrate();
                gridViewString[57]=info.getBowlstats().getOdi().getstrrate();
                gridViewString[58]=info.getBowlstats().getT20().getstrrate();
                gridViewString[59]=info.getBowlstats().getIpl().getstrrate();







                //gridViewString=gridViewString2;
                adapterViewAndroid.notifyDataSetChanged();
                // adapterViewAndroid.refreshEvents(gridViewString);
                androidGridView.invalidateViews();
                androidGridView.setAdapter(adapterViewAndroid);


            }

            @Override
            public void onFailure(Call<Bowling_Items> call, Throwable t) {

            }
        });







        return rootView;
    }

    public void setGridViewHeightBasedOnChildren(GridView gridView, int columns) {
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int items = listAdapter.getCount();
        int rows = 0;

        View listItem = listAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalHeight = listItem.getMeasuredHeight();

        float x = 1;
        if (items > columns) {
            x = items / columns;
            rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);


    }
}
