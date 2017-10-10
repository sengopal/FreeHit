package com.debut.ellipsis.freehit.Stats.Team;

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

        toolbar = (Toolbar) findViewById(R.id.toolbar_team);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("TEAMS");


        TeamItem.add(new TeamListItem(R.drawable.flag_afg, R.string.settings_team_Afghanistan_label));
        TeamItem.add(new TeamListItem(R.drawable.flag_aus, R.string.settings_team_Australia_label));
        TeamItem.add(new TeamListItem(R.drawable.flag_ban, R.string.settings_team_Bangladesh_label));
        TeamItem.add(new TeamListItem(R.drawable.flag_eng, R.string.settings_team_England_label));
        TeamItem.add(new TeamListItem(R.drawable.flag_ind, R.string.settings_team_India_label));
        TeamItem.add(new TeamListItem(R.drawable.flag_ire, R.string.settings_team_Ireland_label));
        TeamItem.add(new TeamListItem(R.drawable.flag_nz, R.string.settings_team_NewZealand_label));
        TeamItem.add(new TeamListItem(R.drawable.flag_pak, R.string.settings_team_Pakistan_label));
        TeamItem.add(new TeamListItem(R.drawable.flag_sa, R.string.settings_team_SouthAfrica_label));
        TeamItem.add(new TeamListItem(R.drawable.flag_sl, R.string.settings_team_SriLanka_label));
        TeamItem.add(new TeamListItem(R.drawable.flag_wi, R.string.settings_team_WestIndies_label));
        TeamItem.add(new TeamListItem(R.drawable.flag_zw, R.string.settings_team_Zimbabwe_label));
        listView = (ListView) findViewById(R.id.Team_list);

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