package com.debut.ellipsis.freehit.More.Team;

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


public class TeamListView extends AppCompatActivity {
    private Toolbar toolbar;
    TeamListAdapter adapter;
    ListView listView;


    public TeamListView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_more_team_list);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        final ArrayList<TeamListItem> TeamItem = new ArrayList<TeamListItem>();

        View viewToolbar = (View) findViewById(R.id.team_list_toolbar);
        toolbar = (Toolbar) viewToolbar.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("TEAMS");


        TeamItem.add(new TeamListItem("https://s.ndtvimg.com/images/entities/120/afghanistan-2156.png", R.string.settings_team_Afghanistan_label));
        TeamItem.add(new TeamListItem("https://s.ndtvimg.com/images/entities/120/australia-7.png", R.string.settings_team_Australia_label));
        TeamItem.add(new TeamListItem("https://s.ndtvimg.com/images/entities/120/bangladesh-2114.png", R.string.settings_team_Bangladesh_label));
        TeamItem.add(new TeamListItem("https://s.ndtvimg.com/images/entities/120/england-5.png", R.string.settings_team_England_label));
        TeamItem.add(new TeamListItem("https://s.ndtvimg.com/images/entities/120/india-6.png", R.string.settings_team_India_label));
        TeamItem.add(new TeamListItem("https://s.ndtvimg.com/images/entities/120/ireland-2123.png", R.string.settings_team_Ireland_label));
        TeamItem.add(new TeamListItem("https://s.ndtvimg.com/images/entities/120/new-zealand-2115.png", R.string.settings_team_NewZealand_label));
        TeamItem.add(new TeamListItem("https://s.ndtvimg.com/images/entities/120/pakistan-2116.png", R.string.settings_team_Pakistan_label));
        TeamItem.add(new TeamListItem("https://s.ndtvimg.com/images/entities/120/south-africa-2117.png", R.string.settings_team_SouthAfrica_label));
        TeamItem.add(new TeamListItem("https://s.ndtvimg.com/images/entities/120/sri-lanka-2118.png", R.string.settings_team_SriLanka_label));
        TeamItem.add(new TeamListItem("https://s.ndtvimg.com/images/entities/120/west-indies-2119.png", R.string.settings_team_WestIndies_label));
        TeamItem.add(new TeamListItem("https://s.ndtvimg.com/images/entities/120/zimbabwe-2120.png", R.string.settings_team_Zimbabwe_label));

        View view = (View) findViewById(R.id.team_list);
        listView = (ListView) view.findViewById(R.id.list);

        adapter = new TeamListAdapter(this, TeamItem);
        listView.setAdapter(adapter);


        listView.setClickable(true);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), TeamActivity.class);
                i.putExtra("CountryName", TeamItem.get(position).getmTeamName());
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
        TeamListView.super.onBackPressed();
        overridePendingTransition(0, R.anim.exit_to_right);

    }


}