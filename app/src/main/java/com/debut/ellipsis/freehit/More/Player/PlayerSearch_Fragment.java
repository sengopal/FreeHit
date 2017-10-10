package com.debut.ellipsis.freehit.More.Player;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.debut.ellipsis.freehit.R;

import java.util.ArrayList;


public class PlayerSearch_Fragment extends ListActivity  {

    ArrayList<String> datasource;
    ArrayList<String> results;
    ArrayAdapter<String> adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_more_player_search_fragment);
        // Get the intent, verify the action and get the query
        final ArrayList<Player_search_item> PlayerItem = new ArrayList<Player_search_item>();
        PlayerItem.add(new Player_search_item("Yuvraj Singh"));
        PlayerItem.add(new Player_search_item("Dinesh karthik"));
        PlayerItem.add(new Player_search_item("Rohit Sharma"));
        PlayerItem.add(new Player_search_item("Virat Kohli"));
        PlayerItem.add(new Player_search_item("Umesh Yadav"));
        PlayerItem.add(new Player_search_item("Axar Patel"));
        PlayerItem.add(new Player_search_item("Lokesh Rahul"));
        PlayerItem.add(new Player_search_item("Kedar Jadhav"));
        ListView listView = (ListView) findViewById(R.id.list);

        Player_search_adapter adapter2 = new Player_search_adapter(this, PlayerItem);
        listView.setAdapter(adapter2);
       // setupResultList();
        //setupDatasource();
        getQuery(getIntent());
        onSearchRequested();
        listView.setClickable(true);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), PlayerActivity.class);
                i.putExtra("PlayerName", PlayerItem.get(position).getmPlayerName());
                startActivity(i);
                setIntent(i);
            }
        });

    }


    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        getQuery(intent);
    }

    private void getQuery(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Intent PlayerIntent = new Intent(this, PlayerActivity.class);
            startActivity(PlayerIntent);
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
        adapter=new ArrayAdapter<String>(this,R.layout.fragment_more_player_activity,R.id.txt,results);
        //set adapter to the ListView
        setListAdapter(adapter);

    }

    public void onResume(){
        super.onResume();
        onSearchRequested();
    }
}
