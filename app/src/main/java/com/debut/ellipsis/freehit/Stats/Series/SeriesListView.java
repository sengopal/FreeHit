package com.debut.ellipsis.freehit.Stats.Series;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;

public class SeriesListView extends AppCompatActivity {
    private Toolbar toolbar;
    SeriesListAdapter adapter;
    ListView listView;


    public SeriesListView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_more_series_listview);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        final ArrayList<SeriesListItem> SeriesItem = new ArrayList<SeriesListItem>();

        toolbar = (Toolbar) findViewById(R.id.toolbar_series);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("SERIES");


        SeriesItem.add(new SeriesListItem(R.drawable.shield, "India"));
        SeriesItem.add(new SeriesListItem(R.drawable.shield, "Sri Lanka"));
        SeriesItem.add(new SeriesListItem(R.drawable.shield, "Bangladesh"));
        SeriesItem.add(new SeriesListItem(R.drawable.shield, "England"));
        SeriesItem.add(new SeriesListItem(R.drawable.shield, "West Indies"));
        SeriesItem.add(new SeriesListItem(R.drawable.shield, "South Africa"));
        
        listView = (ListView) findViewById(R.id.Series_list);

        adapter = new SeriesListAdapter(this, SeriesItem);
        listView.setAdapter(adapter);


        listView.setClickable(true);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), SeriesActivity.class);
                i.putExtra("CountryName", SeriesItem.get(position).getmSeriesName());
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