package com.debut.ellipsis.freehit.Stats.Player;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;

/**
 * Created by Jayanth on 29-09-2017.
 */

public class PlayerSearch_Fragment extends ListActivity  {

    ArrayList<String> datasource;
    ArrayList<String> results;
    ArrayAdapter<String> adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_player_search_fragment);
        // Get the intent, verify the action and get the query
        results=new ArrayList<String>();
        setupResultList();
        setupDatasource();
        getQuery(getIntent());
        onSearchRequested();

    }


    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        getQuery(intent);
    }

    private void getQuery(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doSearch(query);
        }
    }

    public void doSearch(String query){
        //clear the previous results to prevent duplication
        results.clear();
        for(String item:datasource){
            if(item.contains(query))
                results.add(item);
        }

    }

    private void setupDatasource(){
        //create ArrayList object
        datasource=new ArrayList<String>();
        //add some example data
        datasource.add("Yuvraj Singh");
        datasource.add("Dinesh Karthik");
        datasource.add("Rohit Sharma");
        datasource.add("Virat Kohli");
        datasource.add("Umesh Yadav");
        datasource.add("Axar Patel");
        datasource.add("Lokesh Rahul");
        datasource.add("Kedar Jadhav");

    }

    private void setupResultList(){
        results=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(this,R.layout.fragment_stats_player_activity,R.id.txt,results);
        //set adapter to the ListView
        setListAdapter(adapter);

    }

    public void onResume(){
        super.onResume();
        onSearchRequested();
    }
}
