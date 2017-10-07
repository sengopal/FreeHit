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


public class Batting_Fragment extends Fragment {
    String j="odi";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_stats_player_batting_bowling_gridview, container, false);
        final String[] gridViewString=new String[60];
        final GridView androidGridView;
        final Batting_Item_adapter adapterViewAndroid = new Batting_Item_adapter(getContext(), gridViewString);
        androidGridView = (GridView) rootView.findViewById(R.id.grid_view_batting_bowling);
        androidGridView.setAdapter(adapterViewAndroid);

        setHeightDynamically(androidGridView);

        gridViewString[0]= "batting";gridViewString[1]="Test";gridViewString[2]=j;gridViewString[3]="T20";gridViewString[4]="IPL";
        gridViewString[5]="Matches";

        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        Call<Batting_Items> call = apiInterface.doGetBattinInfo();
        call.enqueue(new Callback<Batting_Items>() {
                         @Override
                         public void onResponse(Call<Batting_Items> call, Response<Batting_Items> response) {
                             Batting_Items info = response.body();
                            /* String tmat*/gridViewString[6]=info.getBatstats().getTest().getMatches();
                            /* String omat*/gridViewString[7]=info.getBatstats().getOdi().getMatches();

                             /*String t20mat*/gridViewString[8]=info.getBatstats().getT20().getMatches();
                            /* String imat*/gridViewString[9]=info.getBatstats().getIpl().getMatches();
                             gridViewString[10]="Innings";
                           /*  String tinn*/gridViewString[11]=info.getBatstats().getTest().getInnbat();
                            /* String oinn*/gridViewString[12]=info.getBatstats().getOdi().getInnbat();
                            /* String t20inn*/gridViewString[13]=info.getBatstats().getT20().getInnbat();
                             /*String iinn*/gridViewString[14]=info.getBatstats().getIpl().getInnbat();
                             gridViewString[15]="Not Out";
                             gridViewString[16]=info.getBatstats().getTest().getNotout();
                             gridViewString[17]=info.getBatstats().getOdi().getNotout();
                             gridViewString[18]=info.getBatstats().getT20().getNotout();
                             gridViewString[19]=info.getBatstats().getIpl().getNotout();
                             gridViewString[20]="Runs";
                             gridViewString[21]=info.getBatstats().getTest().getRuns();
                             gridViewString[22]=info.getBatstats().getOdi().getRuns();
                             gridViewString[23]=info.getBatstats().getT20().getRuns();
                             gridViewString[24]=info.getBatstats().getIpl().getRuns();
                             gridViewString[25]="Highest";
                             gridViewString[26]=info.getBatstats().getTest().getHighestinn();
                             gridViewString[27]=info.getBatstats().getOdi().getHighestinn();
                             gridViewString[28]=info.getBatstats().getT20().getHighestinn();
                             gridViewString[29]=info.getBatstats().getIpl().getHighestinn();
                             gridViewString[30]="100s";
                             gridViewString[31]=info.getBatstats().getTest().getHundreds();
                             gridViewString[32]=info.getBatstats().getOdi().getHundreds();
                             gridViewString[33]=info.getBatstats().getT20().getHundreds();
                             gridViewString[34]=info.getBatstats().getIpl().getHundreds();
                             gridViewString[35]="50s";
                             gridViewString[36]=info.getBatstats().getTest().getFifties();
                             gridViewString[37]=info.getBatstats().getOdi().getFifties();
                             gridViewString[38]=info.getBatstats().getT20().getFifties();
                             gridViewString[39]=info.getBatstats().getIpl().getFifties();
                             gridViewString[40]="4s";
                             gridViewString[41]=info.getBatstats().getTest().getFours();
                             gridViewString[42]=info.getBatstats().getOdi().getFours();
                             gridViewString[43]=info.getBatstats().getT20().getFours();
                             gridViewString[44]=info.getBatstats().getIpl().getFours();
                             gridViewString[45]="6s";
                             gridViewString[46]=info.getBatstats().getTest().getSixes();
                             gridViewString[47]=info.getBatstats().getOdi().getSixes();
                             gridViewString[48]=info.getBatstats().getT20().getSixes();
                             gridViewString[49]=info.getBatstats().getIpl().getSixes();
                             gridViewString[50]="avg";
                             gridViewString[51]=info.getBatstats().getTest().getBatavg();
                             gridViewString[52]=info.getBatstats().getOdi().getBatavg();
                             gridViewString[53]=info.getBatstats().getT20().getBatavg();
                             gridViewString[54]=info.getBatstats().getIpl().getBatavg();
                             gridViewString[55]="Strike Rate";
                             gridViewString[56]=info.getBatstats().getTest().getBatstr();
                             gridViewString[57]=info.getBatstats().getOdi().getBatstr();
                             gridViewString[58]=info.getBatstats().getT20().getBatstr();
                             gridViewString[59]=info.getBatstats().getIpl().getBatstr();







                              //gridViewString=gridViewString2;
                             adapterViewAndroid.notifyDataSetChanged();
                             // adapterViewAndroid.refreshEvents(gridViewString);
                             androidGridView.invalidateViews();
                             androidGridView.setAdapter(adapterViewAndroid);


                         }

                         @Override
                         public void onFailure(Call<Batting_Items> call, Throwable t) {

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
        for (int i = 0; i < listAdapter.getCount(); i = i+5) {
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
