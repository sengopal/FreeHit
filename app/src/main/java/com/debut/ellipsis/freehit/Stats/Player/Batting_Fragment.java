package com.debut.ellipsis.freehit.Stats.Player;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Batting_Fragment extends Fragment {
    String j="odi";
    String[] gridViewString=new String[60];/*=

    {
                "Batting", "Test", j, "T20", "IPL",
                "Matches", "tmat", "omat", "t20mat", "imat",
                "Innings", "imat", "oinn", "t20inn", "iinn",
                "N/0", "10", "10", "10", "10",
                "Runs", "10", "10", "10", "10",
                "Highest", "10", "10", "10", "10",
                "100s", "10", "10", "10", "10",
                "50s", "10", "10", "10", "10",
                "4s", "10", "10", "10", "10",
                "6s", "10", "10", "10", "10",
                "Avg", "10", "10", "10", "10",
                "Strike Rate", "10", "10", "10", "10",
    };*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        gridViewString[0]= "batting";gridViewString[1]="Test";gridViewString[2]=j;gridViewString[3]="T20";gridViewString[4]="IPL";
        gridViewString[5]="Matches";
        Batting_Item_adapter adapterViewAndroid = new Batting_Item_adapter(getContext(), gridViewString);

        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        Call<Batting_Items> call = apiInterface.doGetBattinInfo();
        call.enqueue(new Callback<Batting_Items>() {
                         @Override
                         public void onResponse(Call<Batting_Items> call, Response<Batting_Items> response) {
                             Batting_Items info = response.body();
                            /* String tmat*/gridViewString[6]=info.getBatstats().getTest().getMatches();
                            /* String omat*/gridViewString[7]=info.getBatstats().getOdi().getMatches();
                             Toast.makeText(getContext(), info.getBatstats().getTest().getMatches(), Toast.LENGTH_LONG).show();
                             /*String t20mat*/gridViewString[8]=info.getBatstats().getT20().getMatches();
                            /* String imat*/gridViewString[9]=info.getBatstats().getIpl().getMatches();
                             gridViewString[10]="Innings";
                           /*  String tinn*/gridViewString[11]=info.getBatstats().getTest().getInnbat();
                            /* String oinn*/gridViewString[12]=info.getBatstats().getOdi().getInnbat();
                            /* String t20inn*/gridViewString[13]=info.getBatstats().getT20().getInnbat();
                             /*String iinn*/gridViewString[14]=info.getBatstats().getIpl().getInnbat();




                              /*gridViewString = new String[]{
                                      "Batting", "Test", "Odi", "T20", "IPL",
                                      "Matches", tmat, omat, t20mat, imat,
                                      "Innings", imat, oinn, t20inn, iinn,
                                      "N/0", "10", "10", "10", "10",
                                      "Runs", "10", "10", "10", "10",
                                      "Highest", "10", "10", "10", "10",
                                      "100s", "10", "10", "10", "10",
                                      "50s", "10", "10", "10", "10",
                                      "4s", "10", "10", "10", "10",
                                      "6s", "10", "10", "10", "10",
                                      "Avg", "10", "10", "10", "10",
                                      "Strike Rate", "10", "10", "10", "10",


                              };*/
                              //gridViewString=gridViewString2;
                     
                         }

                         @Override
                         public void onFailure(Call<Batting_Items> call, Throwable t) {

                         }
                     });



        View rootView = inflater.inflate(R.layout.fragment_stats_player_batting_bowling_gridview, container, false);

        GridView androidGridView;
        androidGridView = (GridView) rootView.findViewById(R.id.grid_view_batting_bowling);
        androidGridView.setAdapter(adapterViewAndroid);
        setGridViewHeightBasedOnChildren(androidGridView, 5);

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
