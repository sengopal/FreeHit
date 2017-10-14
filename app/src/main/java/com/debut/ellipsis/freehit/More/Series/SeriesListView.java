package com.debut.ellipsis.freehit.More.Series;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.PlayerCountryItem;
import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesListView extends AppCompatActivity {
    private Toolbar toolbar;
    SeriesListAdapter adapter;
    ListView listView;
    String team1;
    String team2;
    String date[]=new String[10];


    public SeriesListView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_more_series_listview);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        final ArrayList<SeriesListItem> SeriesItem = new ArrayList<SeriesListItem>();

        View viewToolbar = (View) findViewById(R.id.series_toolbar);

        toolbar = (Toolbar) viewToolbar.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("SERIES");


       /* SeriesItem.add(new SeriesListItem(R.drawable.shield, "India"));
        SeriesItem.add(new SeriesListItem(R.drawable.shield, "Sri Lanka"));
        SeriesItem.add(new SeriesListItem(R.drawable.shield, "Bangladesh"));
        SeriesItem.add(new SeriesListItem(R.drawable.shield, "England"));
        SeriesItem.add(new SeriesListItem(R.drawable.shield, "West Indies"));
        SeriesItem.add(new SeriesListItem(R.drawable.shield, "South Africa"));*/

        View viewListView = (View) findViewById(R.id.series_list);
        listView = (ListView) viewListView.findViewById(R.id.list);
        adapter = new SeriesListAdapter(this, SeriesItem);
        listView.setAdapter(adapter);
        APIInterface apiInterface= ApiClient.getClient().create(APIInterface.class);
        final Call<SeriesApiItem> seriesInfo=apiInterface.doGetSeries();
        seriesInfo.enqueue(new Callback<SeriesApiItem>() {
            @Override
            public void onResponse(Call<SeriesApiItem> call, Response<SeriesApiItem> response) {
                List<SeriesApiItem> seriesInfo = response.body().getResults();
                for (int i = 0; i <seriesInfo.size() ; i++) {
                   String st= seriesInfo.get(i).getTitle();
                     team1=seriesInfo.get(i).getTeam1();
                     team2=seriesInfo.get(i).getTeam2();
                    date[i]=seriesInfo.get(i).getDate();

                    String t=team1+" vs "+team2;
                    SeriesItem.add(new SeriesListItem(R.drawable.shield, t));
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);




                }
            }

            @Override
            public void onFailure(Call<SeriesApiItem> call, Throwable t) {

            }
        });

        adapter = new SeriesListAdapter(this, SeriesItem);
        listView.setAdapter(adapter);


        listView.setClickable(true);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), SeriesActivity.class);
                i.putExtra("CountryName", SeriesItem.get(position).getmSeriesName());
                i.putExtra("date", date[position]);
                startActivity(i);
                setIntent(i);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(0, R.anim.exit_to_right);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        SeriesListView.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);

    }


}