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
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Batting_Fragment extends Fragment {

    private ProgressBar mProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Intent i = getActivity().getIntent();
        PlayerActivity.player_url = i.getStringExtra("player_url");

        View rootView = inflater.inflate(R.layout.fragment_more_player_batting_bowling_gridview, container, false);
        final String[] gridViewString = new String[60];

        final GridView androidGridView;
        final Batting_Bowling_item_adapter adapterViewAndroid = new Batting_Bowling_item_adapter(getContext(), gridViewString);

        View viewProgress = rootView.findViewById(R.id.progress);
        mProgressBar = (ProgressBar) viewProgress.findViewById(R.id.progress_bar);

        androidGridView = (GridView) rootView.findViewById(R.id.grid_view_batting_bowling);
        androidGridView.setAdapter(adapterViewAndroid);

        setHeightDynamically(androidGridView);

        //Labels For Batting
        gridViewString[0] = "BATTING";
        gridViewString[1] = "TEST";
        gridViewString[2] = "ODI";
        gridViewString[3] = "T20";
        gridViewString[4] = "IPL";
        gridViewString[5] = "MATCHES";
        gridViewString[10] = "INNINGS";
        gridViewString[15] = "NOT OUT";
        gridViewString[20] = "RUNS";
        gridViewString[25] = "HIGHEST";
        gridViewString[30] = "100s";
        gridViewString[35] = "50s";
        gridViewString[40] = "4s";
        gridViewString[45] = "6s";
        gridViewString[50] = "AVERAGE";
        gridViewString[55] = "STRIKE\nRATE";

        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        Call<BattingItem> call = apiInterface.doGetBattingInfo(PlayerActivity.player_url);
        call.enqueue(new Callback<BattingItem>() {
            @Override
            public void onResponse(Call<BattingItem> call, Response<BattingItem> response) {
                BattingItem info = response.body();

                //Number Of Matches in Test,Odi,T20,IPL
                gridViewString[6] = info.getBatstats().getTest().getMatches();
                gridViewString[7] = info.getBatstats().getOdi().getMatches();
                gridViewString[8] = info.getBatstats().getT20().getMatches();
                gridViewString[9] = info.getBatstats().getIpl().getMatches();

                //Number Of Innings Played in Test,Odi,T20,IPL
                gridViewString[11] = info.getBatstats().getTest().getInnbat();
                gridViewString[12] = info.getBatstats().getOdi().getInnbat();
                gridViewString[13] = info.getBatstats().getT20().getInnbat();
                gridViewString[14] = info.getBatstats().getIpl().getInnbat();

                //Number of Not Outs in Test,Odi,T20,IPL
                gridViewString[16] = info.getBatstats().getTest().getNotout();
                gridViewString[17] = info.getBatstats().getOdi().getNotout();
                gridViewString[18] = info.getBatstats().getT20().getNotout();
                gridViewString[19] = info.getBatstats().getIpl().getNotout();

                //Number Of Runs in Test,Odi,T20,IPL
                gridViewString[21] = info.getBatstats().getTest().getRuns();
                gridViewString[22] = info.getBatstats().getOdi().getRuns();
                gridViewString[23] = info.getBatstats().getT20().getRuns();
                gridViewString[24] = info.getBatstats().getIpl().getRuns();

                //Highest Score in Test,Odi,T20,IPL
                gridViewString[26] = info.getBatstats().getTest().getHighestinn();
                gridViewString[27] = info.getBatstats().getOdi().getHighestinn();
                gridViewString[28] = info.getBatstats().getT20().getHighestinn();
                gridViewString[29] = info.getBatstats().getIpl().getHighestinn();

                //Centuries in Test,Odi,T20,IPL
                gridViewString[31] = info.getBatstats().getTest().getHundreds();
                gridViewString[32] = info.getBatstats().getOdi().getHundreds();
                gridViewString[33] = info.getBatstats().getT20().getHundreds();
                gridViewString[34] = info.getBatstats().getIpl().getHundreds();

                //Fifties in Test,Odi,T20,IPL
                gridViewString[36] = info.getBatstats().getTest().getFifties();
                gridViewString[37] = info.getBatstats().getOdi().getFifties();
                gridViewString[38] = info.getBatstats().getT20().getFifties();
                gridViewString[39] = info.getBatstats().getIpl().getFifties();

                //Fours in Test,Odi,T20,IPL
                gridViewString[41] = info.getBatstats().getTest().getFours();
                gridViewString[42] = info.getBatstats().getOdi().getFours();
                gridViewString[43] = info.getBatstats().getT20().getFours();
                gridViewString[44] = info.getBatstats().getIpl().getFours();

                //Sixes in Test,Odi,T20,IPL
                gridViewString[46] = info.getBatstats().getTest().getSixes();
                gridViewString[47] = info.getBatstats().getOdi().getSixes();
                gridViewString[48] = info.getBatstats().getT20().getSixes();
                gridViewString[49] = info.getBatstats().getIpl().getSixes();

                //batting Average in Test,Odi,T20,IPL
                gridViewString[51] = info.getBatstats().getTest().getBatavg();
                gridViewString[52] = info.getBatstats().getOdi().getBatavg();
                gridViewString[53] = info.getBatstats().getT20().getBatavg();
                gridViewString[54] = info.getBatstats().getIpl().getBatavg();

                //Batting Strike Rate in Test,Odi,T20,IPL
                gridViewString[56] = info.getBatstats().getTest().getBatstr();
                gridViewString[57] = info.getBatstats().getOdi().getBatstr();
                gridViewString[58] = info.getBatstats().getT20().getBatstr();
                gridViewString[59] = info.getBatstats().getIpl().getBatstr();


                adapterViewAndroid.notifyDataSetChanged();
                androidGridView.invalidateViews();

                mProgressBar.setVisibility(View.INVISIBLE);
                androidGridView.setAdapter(adapterViewAndroid);

            }

            @Override
            public void onFailure(Call<BattingItem> call, Throwable t) {
                Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
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
